package com.example.social_network.infrastructure.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.social_network.domain.service.UserService;
import com.example.social_network.infrastructure.common.ResponseBase;
import com.example.social_network.infrastructure.dto.auth.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Autenticación", description = "Autenticación de usuarios")
public class AuthController {

    private final UserService authService;

    public AuthController(UserService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    @Operation(summary = "Iniciar sesión", description = "Inicia sesión a partir de email y contraseña")
    public ResponseEntity<ResponseBase<AuthResponseDto>> login(@RequestBody AuthRequestDto request) {
        AuthResponseDto response = authService.login(request);
        return ResponseEntity.ok(ResponseBase.success(response, 200, "Inicio de sesión exitoso!"));
    }
}

