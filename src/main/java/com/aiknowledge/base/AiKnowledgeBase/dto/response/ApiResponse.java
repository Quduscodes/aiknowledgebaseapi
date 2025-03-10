package com.aiknowledge.base.AiKnowledgeBase.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ApiResponse<T> {
    @NonNull
    private String status;
    @NonNull
    private String message;
    private T data;

    public static <T> ApiResponse<T> success(String message, @NonNull T data) {
        return new ApiResponse<>("success", message, data);
    }

    public static <T> ApiResponse<T> error(String message, @NonNull T data) {
        return new ApiResponse<>("error", message, data);
    }
}
