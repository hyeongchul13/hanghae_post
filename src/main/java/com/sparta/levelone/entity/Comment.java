package com.sparta.levelone.entity;

import com.sparta.levelone.dto.CommentRequestDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
@Table(name = "comment")
public class Comment extends Timestamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "username", nullable = false)
    private String username;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


    @ManyToOne(fetch = FetchType.LAZY)
    private Post posts;

    public Comment(CommentRequestDto requestDto, String username) {
        this.content = requestDto.getContent();
        this.username = username;
    }

    public void setPostsAndUsers(Post posts, User user) {
        this.posts = posts;
        this.user = user;
        posts.getCommentList().add(this);
    }

    public void update(CommentRequestDto requestDto) {
        this.content = requestDto.getContent();
        this.username = this.user.getUsername();
    }
}
