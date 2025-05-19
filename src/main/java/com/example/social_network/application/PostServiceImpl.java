package com.example.social_network.application;

import com.example.social_network.domain.Post;
import com.example.social_network.domain.User;
import com.example.social_network.infrastructure.dto.post.*;
import java.util.stream.Collectors;
import com.example.social_network.infrastructure.mapper.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.example.social_network.domain.repository.*;
import com.example.social_network.domain.service.PostService;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public PostServiceImpl(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    public Post createPost(Post request) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User author = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado (" + email + ")"));

        Post post = new Post();
        post.setContent(request.getContent());
        post.setAuthor(author);

        return postRepository.save(post);
    }

    public List<PostResponseDto> getAllPosts() {
        List<Post> posts = postRepository.getAllPosts();
        return posts.stream()
                .map(post -> {
                    int likeCount = likeRepository.countByPostId(post.getId());
                    return PostMapper.toDto(post, likeCount);
                })
                .collect(Collectors.toList());
    }

    public List<PostResponseDto> findByAuthorId(Long authorId) {
        List<Post> posts = postRepository.findByAuthorId(authorId);
        return posts.stream()
                .map(post -> {
                    int likeCount = likeRepository.countByPostId(post.getId());
                    return PostMapper.toDto(post, likeCount);
                })
                .collect(Collectors.toList());
    }
}
