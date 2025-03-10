package com.aiknowledge.base.AiKnowledgeBase.repository;


import com.aiknowledge.base.AiKnowledgeBase.entity.Content;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContentRepository extends JpaRepository<Content, UUID> {
    Optional<List<Content>> findByUserId(String userId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Content c WHERE c.userId = :userId")
    void deleteByUserId(@Param("userId") String userId);
}
