package com.aiknowledge.base.AiKnowledgeBase.service;

import com.aiknowledge.base.AiKnowledgeBase.entity.Content;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface ContentService {
    List<Content> getContentForUser(String userId);

    public CompletableFuture<Void> generateContentForUser(String email);

    void updateContent(String userId, List<Content> contentList);

    void deleteContentForUser(String userId);
}
