package com.example.social_network.infrastructure.persistence.jpa;

import com.example.social_network.domain.User;
import com.example.social_network.domain.repository.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class UserRepositoryJpaAdapter implements UserRepository {

    private final UserRepositoryJpa userRepositoryJpa;

    public UserRepositoryJpaAdapter(UserRepositoryJpa userRepositoryJpa) {
        this.userRepositoryJpa = userRepositoryJpa;
    }

    @Override
    public User save(User user) {
        return userRepositoryJpa.save(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepositoryJpa.findByEmail(email);
    }

    @Override
    public Optional<User> findById(long userId) {
        return userRepositoryJpa.findById(userId);
    }
}
