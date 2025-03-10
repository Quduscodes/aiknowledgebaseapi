package com.aiknowledge.base.AiKnowledgeBase.controller;

import com.aiknowledge.base.AiKnowledgeBase.dto.request.ChangePasswordDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.request.UpdateUserDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.ApiResponse;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.UserDetailsResponseDTO;
import com.aiknowledge.base.AiKnowledgeBase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/profile")
    public ResponseEntity<ApiResponse<UserDetailsResponseDTO>> getUserProfile(Authentication authentication) {
        UserDetailsResponseDTO response = userService.getUserDetails(authentication.getName());
        return ResponseEntity.ok(new ApiResponse<>("success", "Fetched user's details Successfully", response));
    }

    @PutMapping
    public ResponseEntity<ApiResponse<UserDetailsResponseDTO>> updateUser(@RequestBody UpdateUserDTO updateUserDTO, Authentication authentication) {
        UserDetailsResponseDTO result = userService.updateUser(authentication.getName(), updateUserDTO);

        return ResponseEntity.ok(new ApiResponse<>("success", "Updated user's details Successfully", result));
    }

    @PostMapping("/changePassword")
    public ResponseEntity<ApiResponse<UserDetailsResponseDTO>> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO, Authentication authentication) {
        UserDetailsResponseDTO result = userService.changePassword(authentication.getName(), changePasswordDTO);
        return ResponseEntity.ok(new ApiResponse<>("success", "Password changed Successfully", result));
    }
}
