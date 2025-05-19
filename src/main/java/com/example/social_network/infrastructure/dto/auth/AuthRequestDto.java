package com.example.social_network.infrastructure.dto.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class AuthRequestDto {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Ingresa una dirección de correo válida")
    private String email;

    @NotBlank(message = "Debes indicar una contraseña")
    private String password;

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
}
