package com.aiknowledge.base.AiKnowledgeBase.dto.request;

import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserDTO {

    private String firstName;

    private String lastName;

    private String userName;

    private List<String> topics;
}
