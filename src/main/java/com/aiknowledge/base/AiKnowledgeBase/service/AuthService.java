package com.aiknowledge.base.AiKnowledgeBase.service;

import com.aiknowledge.base.AiKnowledgeBase.dto.request.LoginRequestDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.request.RegisterRequestDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.ApiResponse;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.AuthResponseDTO;

public interface AuthService {
    ApiResponse<AuthResponseDTO> register(RegisterRequestDTO request);
    ApiResponse<AuthResponseDTO> login(LoginRequestDTO request);
}
