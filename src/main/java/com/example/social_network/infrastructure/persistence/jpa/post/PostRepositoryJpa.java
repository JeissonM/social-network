package com.example.social_network.infrastructure.persistence.jpa.post;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.social_network.domain.Post;
import java.util.List;

public interface PostRepositoryJpa  extends JpaRepository<Post, Long> {
    List<Post> findByAuthorId(Long authorId);
}
