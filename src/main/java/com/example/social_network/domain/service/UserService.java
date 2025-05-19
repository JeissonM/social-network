package com.example.social_network.domain.service;
import com.example.social_network.domain.User;
import com.example.social_network.infrastructure.dto.auth.AuthRequestDto;
import com.example.social_network.infrastructure.dto.auth.AuthResponseDto;

public interface UserService {

    User register(User user);
    AuthResponseDto login(AuthRequestDto loginRequest);
    User findByEmail(String email);
}
