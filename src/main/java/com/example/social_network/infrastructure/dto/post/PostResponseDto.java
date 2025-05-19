package com.example.social_network.infrastructure.dto.post;

import java.time.LocalDateTime;

public class PostResponseDto {
    private Long id;
    private String content;
    private LocalDateTime createdAt;
    private Long authorId;
    private String author;
    private int likeCount;

    // Getter y Setter
    public void setContent(String content) {this.content = content; }
    public String getContent(){ return this.content; }
    
    public void setCreatedAt(LocalDateTime createdAt) {this.createdAt = createdAt; }
    public LocalDateTime getCreatedAt(){ return this.createdAt; }
    
    public void setId(Long id) {this.id = id; }
    public Long getId(){ return this.id; }

    public void setAuthor(String author) {this.author = author; }
    public String getAuthor(){ return this.author; }

    public void setAuthorId(Long authorId) {this.authorId = authorId; }
    public Long getAuthorId(){ return this.authorId; }
    
    public void setLikeCount(int likeCount) {this.likeCount = likeCount; }
    public int getLikeCount(){ return this.likeCount; }
}
