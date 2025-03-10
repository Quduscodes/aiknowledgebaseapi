package com.aiknowledge.base.AiKnowledgeBase.service;

import com.aiknowledge.base.AiKnowledgeBase.dto.request.ChangePasswordDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.request.UpdateUserDTO;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.UserDetailsResponseDTO;

public interface UserService {
    UserDetailsResponseDTO getUserDetails(String email);

    UserDetailsResponseDTO updateUser(String email, UpdateUserDTO updateUserDTO);

    UserDetailsResponseDTO changePassword(String email, ChangePasswordDTO changePasswordDTO);
}
