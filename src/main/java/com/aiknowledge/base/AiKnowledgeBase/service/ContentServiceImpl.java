package com.aiknowledge.base.AiKnowledgeBase.service;

import com.aiknowledge.base.AiKnowledgeBase.entity.Content;
import com.aiknowledge.base.AiKnowledgeBase.entity.User;
import com.aiknowledge.base.AiKnowledgeBase.repository.ContentRepository;
import com.aiknowledge.base.AiKnowledgeBase.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ContentServiceImpl implements ContentService {

    private final ContentRepository contentRepository;
    private final UserRepository userRepository;
//    private final AiContentGenerator aiContentGenerator; // To be created later

    @Override
    public List<Content> getContentForUser(String userId) {
        userRepository.findById(UUID.fromString(userId))
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Optional<List<Content>> result = contentRepository.findByUserId(userId);

        List<Content> contents = List.of();
        if (result.isPresent()) {
            contents = result.get();
        }
        return contents;
    }

    @Async
    @Override
    public CompletableFuture<Void> generateContentForUser(String email) {
        return CompletableFuture.runAsync(() -> {
            System.out.println("Called generate content for user:" + email + "\n\n\n\n");
            User user = userRepository.findByEmail(email)
                    .orElseThrow(() -> new IllegalArgumentException("User not found"));

            List<String> topics = user.getTopics();

            // Check if content already exists
            Optional<List<Content>> existingUserContentResult = contentRepository.findByUserId(user.getId().toString());

            if (existingUserContentResult.isPresent()) {
                List<Content> existingContent = existingUserContentResult.get();
                if (!existingContent.isEmpty()) {
                    // Check if content is outdated (e.g., less than 2 days to expiry)
                    boolean needsUpdate = existingContent.stream().anyMatch(content ->
                            content.getExpiryDate().isBefore(LocalDate.now().plusDays(2))
                    );

                    if (!needsUpdate) {
                        System.out.println("Content is still valid, no update needed for user:" + email + "\n\n\n\n");
                        return; // Content is still valid, no update needed
                    }

                    // Delete old content
                    contentRepository.deleteAllById(existingContent.stream().map(Content::getId).collect(Collectors.toList()));
                    System.out.println("Delete old content for user:" + email + "\n\n\n\n");
                }
            }

            // Generate new content via AI model
//        List<Content> newContent = aiContentGenerator.generateContent(topics);
            List<Content> newContent = List.of();
            System.out.println("Tried to generate content for user:" + email + "\n\n\n\n");

            // Save generated content with expiry date
            newContent.forEach(content -> content.setExpiryDate(LocalDate.now().plusDays(7)));
            contentRepository.saveAll(newContent);
        });
    }

    @Override
    public void updateContent(String userId, List<Content> newContent) {
        deleteContentForUser(userId);  // Remove old content
        contentRepository.saveAll(newContent);     // Save new content
    }

    @Override
    public void deleteContentForUser(String userId) {
        contentRepository.deleteByUserId(userId);
    }
}

