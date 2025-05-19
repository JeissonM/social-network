package com.example.social_network.infrastructure.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import jakarta.validation.constraints.*;

public class UserRequestDto {

    @NotBlank(message = "El nombre es obligatorio")
    private String names;

    @NotBlank(message = "El apellido es obligatorio")
    private String lastName;

    @NotBlank(message = "El alias es obligatorio")
    private String alias;

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Ingresa una dirección de correo válida")
    private String email;

    @NotBlank(message = "Debes indicar una contraseña")
    private String password;

    @NotNull(message = "Debes ingresar tu fecha de nacimiento")
    @Past(message = "La fecha de nacimiento debe ser inferior a hoy")
    @Schema(description = "Fecha de nacimiento en formato yyyy-MM-dd", example = "1990-01-01")
    private LocalDate birthDate;

    // Getters y Setters
    public String getNames() { return names; }
    public void setNames(String names) { this.names = names; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAlias() { return alias; }
    public void setAlias(String alias) { this.alias = alias; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public LocalDate getBirthDate() { return birthDate; }
    public void setBirthDate(LocalDate birthDate) { this.birthDate = birthDate; }
}
