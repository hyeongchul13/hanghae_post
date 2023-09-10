package com.sparta.levelone.controller;

import com.sparta.levelone.dto.CommentDeleteDto;
import com.sparta.levelone.dto.CommentRequestDto;
import com.sparta.levelone.dto.CommentResponseDto;
import com.sparta.levelone.service.CommentService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CommnetController {

    private final CommentService commentService;


    // 1. 댓글 작성
    @PostMapping("/comment/{id}")
    public CommentResponseDto createcomment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.createcomment(id, requestDto, request);
    }
    // 2. 댓글 수정

    @PutMapping("/comment/{id}")
    public CommentResponseDto updatecomment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.updatecomment(id, requestDto, request);
    }

    // 2. 댓글 삭제
    @DeleteMapping("/comment/{id}")
    public CommentDeleteDto deletecomment(@PathVariable Long id, HttpServletRequest request) {
        return commentService.deletecomment(id, request);
    }
}
