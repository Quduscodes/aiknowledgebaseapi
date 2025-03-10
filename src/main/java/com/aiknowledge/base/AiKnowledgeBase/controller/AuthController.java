package com.aiknowledge.base.AiKnowledgeBase.controller;

import com.aiknowledge.base.AiKnowledgeBase.dto.request.LoginRequestDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.request.RegisterRequestDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.ApiResponse;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.AuthResponseDTO;
import com.aiknowledge.base.AiKnowledgeBase.service.AuthService;
import com.aiknowledge.base.AiKnowledgeBase.service.ContentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final ContentService contentService;


    @Autowired
    public AuthController(AuthService authService, ContentService contentService) {
        this.authService = authService;
        this.contentService = contentService;
    }


    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> loginUser(@Valid @RequestBody LoginRequestDTO loginRequestDTO) {
        ApiResponse<AuthResponseDTO> response = authService.login(loginRequestDTO);
        contentService.generateContentForUser(loginRequestDTO.getEmail());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponseDTO>> register(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        ApiResponse<AuthResponseDTO> response = authService.register(registerRequestDTO);
        contentService.generateContentForUser(registerRequestDTO.getEmail());
        return ResponseEntity.ok(response);
    }
}
