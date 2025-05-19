package com.example.social_network.infrastructure.dto.post;

import jakarta.validation.constraints.NotBlank;

public class PostRequestDto {
    @NotBlank(message = "El contenido del post es obligatorio")
    private String content;

    // Getter y Setter
    public void setContent(String content) {this.content = content; }
    public String getContent(){ return this.content; }
}
