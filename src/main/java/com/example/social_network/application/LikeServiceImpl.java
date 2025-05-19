package com.example.social_network.application;

import java.util.Optional;
import com.example.social_network.domain.Like;
import com.example.social_network.domain.Post;
import com.example.social_network.domain.User;
import com.example.social_network.domain.repository.LikeRepository;
import com.example.social_network.domain.repository.PostRepository;
import com.example.social_network.domain.repository.UserRepository;
import com.example.social_network.domain.service.LikeService;
import org.springframework.stereotype.Service;

@Service
public class LikeServiceImpl implements LikeService {

    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final LikeRepository likeRepository;

    public LikeServiceImpl(PostRepository postRepository, UserRepository userRepository, LikeRepository likeRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.likeRepository = likeRepository;
    }

    @Override
    public void likePost(Long userId, Long postId) {
        if (userId == 0 || postId == 0) {
            throw new IllegalArgumentException("Debes indicar un usuario y un post");
        }

        Optional<User> userOpt = userRepository.findById(userId);
        Optional<Post> postOpt = postRepository.findById(postId);

        if (userOpt.isEmpty() || postOpt.isEmpty()) {
            throw new IllegalArgumentException("Usuario o publicación no encontrados");
        }

        // Verificamos si ya le dio like
        boolean alreadyLiked = likeRepository.existsByUser_IdAndPost_Id(userId, postId);
        if (alreadyLiked) {
            throw new IllegalStateException("Estimado usuario, ya le dio like a esta publicación");
        }

        Like like = new Like();
        like.setUser(userOpt.get());
        like.setPost(postOpt.get());

        likeRepository.save(like);
    }
}
