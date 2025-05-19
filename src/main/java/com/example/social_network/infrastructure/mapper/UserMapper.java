package com.example.social_network.infrastructure.mapper;

import com.example.social_network.domain.User;
import com.example.social_network.infrastructure.dto.UserRequestDto;
import com.example.social_network.infrastructure.dto.UserResponseDto;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.time.LocalDateTime;

public class UserMapper {

    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static User toEntity(UserRequestDto dto) {
        User user = new User();
        user.setNames(dto.getNames());
        user.setLastName(dto.getLastName());
        user.setAlias(dto.getAlias());
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setBirthDate(dto.getBirthDate());
        user.setCreatedAt(LocalDateTime.now());
        return user;
    }

    public static UserResponseDto toDto(User user) {
        UserResponseDto dto = new UserResponseDto();
        dto.setId(user.getId());
        dto.setNames(user.getNames());
        dto.setLastName(user.getLastName());
        dto.setAlias(user.getAlias());
        dto.setEmail(user.getEmail());
        dto.setBirthDate(user.getBirthDate());
        dto.setCreatedAt(user.getCreatedAt());
        return dto;
    }
}
