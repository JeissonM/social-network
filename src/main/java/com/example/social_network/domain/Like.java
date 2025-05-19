package com.example.social_network.domain;

import jakarta.persistence.*;

@Entity
@Table(name = "likes")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;

    // Getters y Setters

    public void setUser(User user){ this.user = user; }
    public User getUser(){ return this.user; }
    
    public void setPost(Post post){ this.post = post; }
    public Post getPost(){ return this.post; }
}
