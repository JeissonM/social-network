package com.example.social_network.domain.repository;

import com.example.social_network.domain.Like;

public interface LikeRepository {
    Like save(Like like);
    boolean existsByUser_IdAndPost_Id(Long user_id, Long post_id);
    int countByPostId(Long postId);
}