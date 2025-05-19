package com.example.social_network.infrastructure.persistence.jpa.post;

import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

import com.example.social_network.domain.Post;
import com.example.social_network.domain.repository.PostRepository;

@Repository
public class PostRepositoryJpaAdapter implements PostRepository{
    private final PostRepositoryJpa postRepositoryJpa;

    public PostRepositoryJpaAdapter(PostRepositoryJpa postRepositoryJpa) {
        this.postRepositoryJpa = postRepositoryJpa;
    }

    @Override
    public Post save(Post post) {
        return postRepositoryJpa.save(post);
    }

    public List<Post> getAllPosts(){
        return postRepositoryJpa.findAll();
    }

    public List<Post> findByAuthorId(Long authorId){
        return postRepositoryJpa.findByAuthorId(authorId);
    }

    public Optional<Post> findById(long postId) {
        return postRepositoryJpa.findById(postId);
    }
}
