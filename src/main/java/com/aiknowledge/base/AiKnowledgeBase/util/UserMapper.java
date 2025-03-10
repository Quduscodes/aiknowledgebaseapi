package com.aiknowledge.base.AiKnowledgeBase.util;


import com.aiknowledge.base.AiKnowledgeBase.dto.response.UserDetailsResponseDTO;
import com.aiknowledge.base.AiKnowledgeBase.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    /**
     * Converts [User] entity model into [UserDetailsResponseDTO]
     */
    public UserDetailsResponseDTO toUserDetailsDTO(User user) {
        return (UserDetailsResponseDTO) UserDetailsResponseDTO
                .builder()
                .userName(user.getUserName())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .id(user.getId())
                .email(user.getEmail())
                .topics(user.getTopics())
                .build();
    }
}

