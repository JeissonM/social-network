package com.example.social_network.infrastructure.mapper;

import com.example.social_network.infrastructure.dto.post.*;
import com.example.social_network.domain.Post;
import java.time.LocalDateTime;

public class PostMapper {

    public static Post toEntity(PostRequestDto dto) {
        Post post = new Post();
        post.setContent(dto.getContent());
        post.setCreatedAt(LocalDateTime.now());
        return post;
    }

    public static PostResponseDto toDto(Post post, int likeCont) {
        PostResponseDto dto = new PostResponseDto();
        dto.setId(post.getId());
        dto.setAuthor(post.getAuthor().getEmail());
        dto.setContent(post.getContent());
        dto.setCreatedAt(post.getCreatedAt());
        dto.setAuthorId(post.getAuthor().getId());
        dto.setLikeCount(likeCont);
        return dto;
    }
}
