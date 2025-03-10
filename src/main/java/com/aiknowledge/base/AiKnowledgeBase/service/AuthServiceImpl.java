package com.aiknowledge.base.AiKnowledgeBase.service;

import com.aiknowledge.base.AiKnowledgeBase.dto.request.LoginRequestDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.request.RegisterRequestDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.ApiResponse;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.AuthResponseDTO;
import com.aiknowledge.base.AiKnowledgeBase.entity.User;
import com.aiknowledge.base.AiKnowledgeBase.enums.UserRolesEnum;
import com.aiknowledge.base.AiKnowledgeBase.exception.IllegalRequestException;
import com.aiknowledge.base.AiKnowledgeBase.repository.UserRepository;
import com.aiknowledge.base.AiKnowledgeBase.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public ApiResponse<AuthResponseDTO> register(RegisterRequestDTO request) {
        Optional<User> result = userRepository.findByEmail(request.getEmail());
        // Check if user already exists
        if (result.isPresent()) {
            throw new IllegalRequestException("User with this email already exists.");
        }

        // Create new user
        User newUser = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword())) // Encrypt password
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .role(UserRolesEnum.USER) // Default role
                .topics(request.getTopics())
                .build();

        userRepository.save(newUser);

        // Generate JWT token
        String token = jwtUtil.generateToken(newUser.getEmail(), newUser.getRole().toString());

        return new ApiResponse<>("success", "Registration successful", new AuthResponseDTO(token));
    }

    @Override
    public ApiResponse<AuthResponseDTO> login(LoginRequestDTO request) {
        // Find user by email
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalRequestException("Invalid email or password."));

        // Verify password
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new IllegalRequestException("Invalid email or password.");
        }

        // Generate JWT token
        String token = jwtUtil.generateToken(user.getEmail(), user.getRole().toString());

        return new ApiResponse<>("success", "Login successful", new AuthResponseDTO(token));

    }
}
