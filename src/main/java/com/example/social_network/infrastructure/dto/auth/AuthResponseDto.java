package com.example.social_network.infrastructure.dto.auth;

import com.example.social_network.infrastructure.dto.UserResponseDto;

public class AuthResponseDto {
    private String token;
    private UserResponseDto user;

    // Getters y Setters

    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }
    public void setUser(UserResponseDto _user) {this.user = _user; }
    public UserResponseDto getUserDto() {return this.user; }

}
