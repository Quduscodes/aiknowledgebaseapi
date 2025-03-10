package com.aiknowledge.base.AiKnowledgeBase.controller;

import com.aiknowledge.base.AiKnowledgeBase.dto.response.ApiResponse;
import com.aiknowledge.base.AiKnowledgeBase.dto.response.UserDetailsResponseDTO;
import com.aiknowledge.base.AiKnowledgeBase.entity.Content;
import com.aiknowledge.base.AiKnowledgeBase.service.ContentService;
import com.aiknowledge.base.AiKnowledgeBase.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contents")
public class ContentsController {

    private final ContentService contentService;
    private final UserService userService;

    @Autowired
    public ContentsController(ContentService contentService,UserService userService ) {
        this.contentService = contentService;
        this.userService = userService;
    }


    @GetMapping
    public ResponseEntity<ApiResponse<List<Content>>> getUsersContents(Authentication authentication) {
        UserDetailsResponseDTO result = userService.getUserDetails(authentication.getName());

        List<Content> response = contentService.getContentForUser(result.getId().toString());
        return ResponseEntity.ok(new ApiResponse<>("success", "User's contents fetched Successfully", response));
    }

    @PostMapping
    public ResponseEntity<ApiResponse<?>> updateContents(@RequestBody List<Content> contents, Authentication authentication) {
        UserDetailsResponseDTO result = userService.getUserDetails(authentication.getName());
        contentService.updateContent(result.getId().toString(), contents);
        return ResponseEntity.ok(new ApiResponse<>("success", "User's contents updated Successfully", null));
    }

    @DeleteMapping
    public ResponseEntity<ApiResponse<?>> deleteUsersContents(Authentication authentication) {
        UserDetailsResponseDTO result = userService.getUserDetails(authentication.getName());
        contentService.deleteContentForUser(result.getId().toString());
        return ResponseEntity.ok(new ApiResponse<>("success", "User's contents deleted Successfully", null));
    }
}
