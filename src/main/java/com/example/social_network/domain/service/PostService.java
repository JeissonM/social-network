package com.example.social_network.domain.service;

import com.example.social_network.domain.Post;
import com.example.social_network.infrastructure.dto.post.PostResponseDto;
import java.util.List;

public interface PostService {

    Post createPost(Post post);
    List<PostResponseDto> getAllPosts();
    List<PostResponseDto> findByAuthorId(Long authorId);
}
