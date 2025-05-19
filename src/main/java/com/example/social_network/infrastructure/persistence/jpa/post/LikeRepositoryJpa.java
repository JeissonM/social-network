package com.example.social_network.infrastructure.persistence.jpa.post;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.social_network.domain.Like;

public interface LikeRepositoryJpa   extends JpaRepository<Like, Long> {
    boolean existsByUser_IdAndPost_Id(Long userId, Long postId);
    int countByPost_Id(Long postId);
}
