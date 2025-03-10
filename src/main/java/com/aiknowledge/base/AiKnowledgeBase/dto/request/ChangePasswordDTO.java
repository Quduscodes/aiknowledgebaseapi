package com.aiknowledge.base.AiKnowledgeBase.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ChangePasswordDTO{
    @NotBlank(message = "New Password is required.")
    private String newPassword;

    @NotBlank(message = "Old Password is required.")
    private String oldPassword;
}