package com.example.social_network.domain.repository;

import java.util.List;
import java.util.Optional;
import com.example.social_network.domain.Post;


public interface PostRepository {
    Post save(Post post);
    List<Post> getAllPosts();
    List<Post> findByAuthorId(Long authorId);
    Optional<Post> findById(long postId);
}
