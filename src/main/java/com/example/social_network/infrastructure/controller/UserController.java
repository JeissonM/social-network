package com.example.social_network.infrastructure.controller;

import com.example.social_network.domain.User;
import com.example.social_network.domain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.social_network.infrastructure.dto.*;
import jakarta.validation.Valid;
import com.example.social_network.infrastructure.mapper.UserMapper;
import com.example.social_network.infrastructure.common.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/api/users")
@Tag(name = "Usuarios", description = "Operaciones relacionadas con usuarios")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo usuario", description = "Registra un usuario con sus datos personales.")
    public ResponseEntity<ResponseBase<UserResponseDto>> register(@Valid @RequestBody UserRequestDto userDto) {
        User user = UserMapper.toEntity(userDto);
        User savedUser = userService.register(user);
        UserResponseDto responseDto = UserMapper.toDto(savedUser);
        return ResponseEntity.ok(ResponseBase.success(responseDto, 200, "Usuario registrado con éxito!"));
    }
}
