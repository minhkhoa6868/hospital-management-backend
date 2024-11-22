package com.service;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.model.User;
import com.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // handle login
    public boolean login(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        
        if (user.isPresent()) {
            return passwordEncoder.matches(password, user.get().getPassword());
        } else {
            return false;
        }
    }

    // handle check if user exists
    public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }

    // handle signup
    public User signup(User newUser) {
        return userRepository.save(newUser);
    }
}
