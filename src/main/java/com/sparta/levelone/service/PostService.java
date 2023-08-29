package com.sparta.levelone.service;

import com.sparta.levelone.dto.DeleteResponseDto;
import com.sparta.levelone.repository.PostRepository;
import com.sparta.levelone.dto.PostRequestDto;
import com.sparta.levelone.dto.PostResponseDto;
import com.sparta.levelone.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;

    // 1. 게시글 생성
    public PostResponseDto createPost(PostRequestDto requestDto) {
        Post post = new Post(requestDto);
        postRepository.save(post);
        return new PostResponseDto(post);
    }

    // 2. 게시글 전체 목록 조회
    public List<PostResponseDto> getPostList(){
        List<Post> postList = postRepository.findAllByOrderByModifiedAtDesc();
        List<PostResponseDto> responseDto = new ArrayList<>();
        for (Post postList2 : postList) {
            responseDto.add(new PostResponseDto(postList2));
        }
        return responseDto;
    }

    // 3. 선택한 게시글 조회 -> 예외처리("게시글이 존재하지 않습니다")
    public PostResponseDto getPost(Long id) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글이 존재하지 않습니다.")
        );
        return new PostResponseDto(post);
    }

    // 4. 선택한 게시글 수정 -> ("아이디가 존재하지 않습니다")
    @Transactional
    public PostResponseDto update(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        boolean result = requestDto.getPassword().equals(post.getPassword());
        if (result) {
            post.update(requestDto);
        }
        return new PostResponseDto(post);
    }

    // 5. 선택한 게시글 삭제
    public DeleteResponseDto delete(Long id, PostRequestDto requestDto) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("아이디가 존재하지 않습니다")
        );
        boolean result = requestDto.getPassword().equals(post.getPassword());
        String response;
        if(result){
            postRepository.deleteById(id);
            response = "삭제완료!";
        }else {
            response = "비밀번호가 일치하지 않습니다";
        }
        return new DeleteResponseDto(response);
    }
}
