package com.aiknowledge.base.AiKnowledgeBase.dto.response;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContentResponseDTO {

    private UUID id;

    private UUID userId;

    private String topic;

    private String contentText;

    private String contentType;

    private LocalDate deliveryDate;

    private LocalDate createdAt = LocalDate.now();
}
