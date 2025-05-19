package com.example.social_network.infrastructure.controller;

import com.example.social_network.infrastructure.common.ResponseBase;
import com.example.social_network.infrastructure.dto.post.*;
import com.example.social_network.infrastructure.mapper.PostMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import jakarta.validation.Valid;
import com.example.social_network.domain.Post;
import com.example.social_network.domain.service.PostService;
import com.example.social_network.domain.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
@Tag(name = "Post", description = "Operaciones relacionadas con publicaciones")
public class PostController {
    private final PostService postService;
    private final LikeService likeService;

    public PostController(PostService postService, LikeService likeService) {
        this.postService = postService;
        this.likeService = likeService;
    }

    @PostMapping
    @Operation(summary = "Registrar nuevo post", description = "Registra una nueva publicación para el usuario.")
    public ResponseEntity<ResponseBase<PostResponseDto>> createPost(@Valid @RequestBody PostRequestDto request) {
        Post post = PostMapper.toEntity(request);
        Post savedPost = postService.createPost(post);
        PostResponseDto response = PostMapper.toDto(savedPost, 0);

        return ResponseEntity.ok(ResponseBase.success(response, 200, "Post creado con éxito!"));
    }

    @GetMapping("/posts")
    @Operation(summary = "Listado de posts", description = "Lista la totalidad de las publicaciones.")
    public ResponseEntity<ResponseBase<List<PostResponseDto>>> getAllPosts() {
        List<PostResponseDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(ResponseBase.success(posts, 200, "Posts listados exitosamente"));
    }

    @GetMapping("/posts/user/{authorId}")
    @Operation(summary = "Listado de posts por autor", description = "Lista la totalidad de las publicaciones para un autor en particular.")
    public ResponseEntity<ResponseBase<List<PostResponseDto>>> getPostsByAuthorId(@PathVariable Long authorId) {
        List<PostResponseDto> posts = postService.findByAuthorId(authorId);
        return ResponseEntity.ok(ResponseBase.success(posts, 200, "Posts listados exitosamente"));
    }

    @PostMapping("/posts/like")
    @Operation(summary = "Dar like", description = "Permite dar me gusta a una publicación.")
    public ResponseEntity<ResponseBase<String>> likePost(@RequestParam Long userId, @RequestParam Long postId) {
        likeService.likePost(userId, postId);
        return ResponseEntity.ok(ResponseBase.success("", 200, "Has dado like"));
    }
}
