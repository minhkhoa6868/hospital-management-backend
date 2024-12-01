package com.dto;

import lombok.Getter;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;;

@Getter
@Setter
public class LoginDTO {
    @NotBlank(message = "Username is required")
    private String username;
    @NotBlank(message = "Password is required")
    private String password;
}