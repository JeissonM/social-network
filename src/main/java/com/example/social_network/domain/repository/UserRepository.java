package com.example.social_network.domain.repository;

import com.example.social_network.domain.User;

import java.util.Optional;

public interface UserRepository {
    User save(User user);
    Optional<User> findByEmail(String email);
    Optional<User> findById(long userId);
}
