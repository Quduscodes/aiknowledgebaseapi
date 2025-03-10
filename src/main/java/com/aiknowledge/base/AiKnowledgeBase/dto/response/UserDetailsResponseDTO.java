package com.aiknowledge.base.AiKnowledgeBase.dto.response;

import com.aiknowledge.base.AiKnowledgeBase.enums.UserRolesEnum;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@Builder
public class UserDetailsResponseDTO {

    private UUID id;

    private String email;

    private String firstName;

    private String lastName;

    private String userName;

    private UserRolesEnum role;

    private LocalDateTime createdAt;

    private List<String> topics;
}
