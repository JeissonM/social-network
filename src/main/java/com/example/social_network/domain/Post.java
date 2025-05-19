package com.example.social_network.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "posts")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String content;
    private LocalDateTime createdAt = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Like> likes;

    // Getters y Setters
    public void setContent(String content) {this.content = content; }
    public String getContent(){ return this.content; }
    
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt; }
    public LocalDateTime getCreatedAt(){ return this.createdAt; }
    
    public void setId(Long id) {this.id = id; }
    public Long getId(){ return this.id; }

    public void setAuthor(User author) {this.author = author; }
    public User getAuthor(){ return this.author; }
}
