package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.model.User;
import com.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Signup {
    private final UserService userService;

    private final PasswordEncoder passwordEncoder;

    public Signup(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> signup(@RequestBody User inputUser) {
        boolean isExistingUser = userService.userExists(inputUser.getUsername());

        if (isExistingUser) {
            throw new RuntimeException("User already exists");
        }

        String hashPassword = this.passwordEncoder.encode(inputUser.getPassword());
        inputUser.setPassword(hashPassword);
        inputUser.setAdmin(true);
        User newUser = userService.signup(inputUser);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
    }
}
