package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
