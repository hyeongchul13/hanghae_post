package com.sparta.levelone.repository;///


import com.sparta.levelone.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByOrderByModifiedAtDesc();

    Optional<Post> findByUsername(String username);

    Post findByUserId(long userId);

    Optional<Post> findByUsernameAndId(String username, long id);

}
