package com.example.social_network.application;

import com.example.social_network.domain.User;
import com.example.social_network.domain.repository.UserRepository;
import com.example.social_network.domain.service.UserService;
import com.example.social_network.infrastructure.dto.auth.*;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.example.social_network.infrastructure.security.*;
import com.example.social_network.infrastructure.mapper.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    public User register(User user) {
        userRepository.findByEmail(user.getEmail())
            .ifPresent(existing -> {
                throw new IllegalArgumentException("El correo ya está registrado en otro usuario");
            });

        return userRepository.save(user);
    }

    public AuthResponseDto login(AuthRequestDto request) {
        User user = userRepository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Credenciales inválidas");
        }
        AuthResponseDto response = new AuthResponseDto();
        response.setUser(UserMapper.toDto(user));
        response.setToken(jwtService.generateToken(user));
        return response;
    }

    public User findByEmail(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));
    }
}
