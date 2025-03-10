package com.aiknowledge.base.AiKnowledgeBase.service;

import com.aiknowledge.base.AiKnowledgeBase.dto.request.ChangePasswordDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.request.UpdateUserDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.UserDetailsResponseDTO;
import com.aiknowledge.base.AiKnowledgeBase.entity.User;
import com.aiknowledge.base.AiKnowledgeBase.exception.IllegalRequestException;
import com.aiknowledge.base.AiKnowledgeBase.repository.UserRepository;
import com.aiknowledge.base.AiKnowledgeBase.util.JwtUtil;
import com.aiknowledge.base.AiKnowledgeBase.util.UserMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ContentService contentService;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Override
    public UserDetailsResponseDTO getUserDetails(String email) {
        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalRequestException("User does not exist"));

        return userMapper.toUserDetailsDTO(user);
    }

    @Override
    public UserDetailsResponseDTO updateUser(String email, UpdateUserDTO updateUserDTO) {
        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalRequestException("User does not exist"));

        // Update user details
        if (updateUserDTO.getTopics() != null) {
            user.setTopics(updateUserDTO.getTopics());
            System.out.println("Setting topics \n\n\n\n"+updateUserDTO.getTopics());
            System.out.println(updateUserDTO.getTopics().equals(user.getTopics())+"\n\n\n\n");
            if (!(updateUserDTO.getTopics().equals(user.getTopics()))) {
                System.out.println("Trying to generate content\n\n\n\n");
                contentService.generateContentForUser(user.getEmail());
            }
        }
        if (updateUserDTO.getUserName() != null) {
            user.setUserName(updateUserDTO.getUserName());
        }
        if (updateUserDTO.getFirstName() != null) {
            user.setFirstName(updateUserDTO.getFirstName());
        }
        if (updateUserDTO.getLastName() != null) {
            user.setLastName(updateUserDTO.getLastName());
        }

        userRepository.save(user);

        return userMapper.toUserDetailsDTO(user);
    }

    @Override
    public UserDetailsResponseDTO changePassword(String email, ChangePasswordDTO changePasswordDTO) {
        // Find user by email
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalRequestException("User does not exist"));

        // Verify old password
        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), user.getPassword())) {
            throw new IllegalRequestException("Old password is not correct.");
        }

        user.setPassword(passwordEncoder.encode(changePasswordDTO.getNewPassword()));
        userRepository.save(user);

        return userMapper.toUserDetailsDTO(user);
    }
}
