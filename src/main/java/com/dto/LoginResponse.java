package com.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
    private boolean success;
    private String message;

    // Constructor, getters, setters
    public LoginResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}
