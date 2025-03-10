package com.aiknowledge.base.AiKnowledgeBase.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "content")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Content {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String topic;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String contentText;

    @Column(nullable = false)
    private String contentType; // e.g., text, video, code snippet, etc.

    @Column(nullable = false)
    private LocalDate expiryDate;

    @Column(nullable = false, updatable = false)
    @Builder.Default
    private LocalDate createdAt = LocalDate.now();
}
