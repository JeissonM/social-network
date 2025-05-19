package com.example.social_network.infrastructure.persistence.jpa.post;

import com.example.social_network.domain.Like;
import com.example.social_network.domain.repository.LikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public class LikeRepositoryJpaAdapter implements LikeRepository {
    private final LikeRepositoryJpa likeRepositoryJpa;

    public LikeRepositoryJpaAdapter(LikeRepositoryJpa likeRepositoryJpa) {
        this.likeRepositoryJpa = likeRepositoryJpa;
    }

    @Override
    public Like save(Like like) {
        return likeRepositoryJpa.save(like);
    }

    @Override
    public boolean existsByUser_IdAndPost_Id(Long userId, Long postId) {
        return likeRepositoryJpa.existsByUser_IdAndPost_Id(userId, postId);
    }

    @Override
    public int countByPostId(Long postId) {
        return likeRepositoryJpa.countByPost_Id(postId);
    }
}
