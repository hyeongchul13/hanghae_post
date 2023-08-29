package com.sparta.levelone.controller;

import com.sparta.levelone.dto.DeleteResponseDto;
import com.sparta.levelone.dto.PostRequestDto;
import com.sparta.levelone.dto.PostResponseDto;
import com.sparta.levelone.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class PostController {
    private final PostService postService;

    // 1. 게시글 생성
    @PostMapping("/post")
    public PostResponseDto createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    // 2. 게시글 전체 목록 조회
    @GetMapping("/post")
    public List<PostResponseDto> getPostList() {
        return postService.getPostList();
    }

    // 3. 선택한 게시글 조회
    @GetMapping("/post/{id}")
    public PostResponseDto getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    // 4. 선택한 게시글 수정
    @PutMapping("/post/{id}")
    public PostResponseDto update(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.update(id, requestDto);
    }

    // 5. 선택한 게시글 삭제
    @DeleteMapping("/post/{id}")
    public DeleteResponseDto delete(@PathVariable Long id, @RequestBody PostRequestDto requestDto) {
        return postService.delete(id, requestDto);
    }
}