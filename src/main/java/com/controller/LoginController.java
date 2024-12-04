package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.dto.LoginDTO;
import com.dto.LoginResponse;
import com.service.UserService;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginDTO loginDto) {
        boolean success = userService.login(loginDto.getUsername(), loginDto.getPassword());

        if (success) {
            return ResponseEntity.status(HttpStatus.OK).body(new LoginResponse(true, "Login successful"));
        } else {
            return ResponseEntity.badRequest().body(new LoginResponse(false, "Invalid username or password"));
        }
    }
}
