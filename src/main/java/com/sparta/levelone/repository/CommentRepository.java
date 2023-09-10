package com.sparta.levelone.repository;

import com.sparta.levelone.entity.Comment;
import com.sparta.levelone.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findAllByPosts(Post post);


}
