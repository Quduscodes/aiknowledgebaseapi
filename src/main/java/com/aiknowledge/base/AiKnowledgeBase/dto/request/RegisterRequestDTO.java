package com.aiknowledge.base.AiKnowledgeBase.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDTO {
    private String firstName;
    private String lastName;
    @NotBlank(message = "Email field is required.")
    @Email(message = "Please enter a valid email address", flags = {Pattern.Flag.CASE_INSENSITIVE})
    private String email;
    @NotBlank(message = "Password field is required.")
    private String password;
    private List<String> topics;
}
