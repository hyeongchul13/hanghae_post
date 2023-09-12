package com.sparta.levelone.service;

import com.sparta.levelone.dto.CommentDeleteDto;
import com.sparta.levelone.dto.CommentRequestDto;
import com.sparta.levelone.dto.CommentResponseDto;
import com.sparta.levelone.entity.Comment;
import com.sparta.levelone.entity.Post;
import com.sparta.levelone.entity.User;
import com.sparta.levelone.exception.APIException;
import com.sparta.levelone.exception.ExceptionStatus;
import com.sparta.levelone.jwt.JwtUtil;
import com.sparta.levelone.repository.CommentRepository;
import com.sparta.levelone.repository.PostRepository;
import com.sparta.levelone.repository.UserRepository;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;


    // 1. 댓글 작성
    @Transactional
    public CommentResponseDto createcomment(Long postId, CommentRequestDto requestDto, HttpServletRequest request) {
        // 사용자 정보 가져오기. request에 token 가져오기
        String token = jwtUtil.getJwtFromHeader(request);
        Claims claims;
        // 토큰이 있는 경우에만 글 작성 가능
        // 토큰 검증
        if (jwtUtil.validateToken(token)) {
            // 토큰에서 사용자 정보 가져오기
            claims = jwtUtil.getUserInfoFromToken(token);


            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회 -> 로그인 안했으면 로그인 하라고 메시지
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("로그인 해주세요")
            );

            Post post = postRepository.findById(postId).orElseThrow(
                    () -> new IllegalArgumentException("해당 아이디의 포스트가 없습니다.")
            );

            Comment comment = new Comment(requestDto, user.getUsername());
            comment.setPostsAndUsers(post, user);
            commentRepository.save(comment);
            return new CommentResponseDto(comment);
        }
        throw new APIException(ExceptionStatus.INVALID_TOKEN);
    }

    // 2. 댓글 수정
    @Transactional
    public CommentResponseDto updatecomment(Long Id, CommentRequestDto requestDto, HttpServletRequest request) {
        String token = jwtUtil.getJwtFromHeader(request);
        Claims claims;

        // 토큰 검증
        if (jwtUtil.validateToken(token)) {
            // 토큰에서 사용자 정보 가져오기
            claims = jwtUtil.getUserInfoFromToken(token);

            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회 -> 로그인 안했으면 로그인 하라고 메시지
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("로그인 해주세요")
            );

            Comment comment = commentRepository.findById(Id).orElseThrow(
                    () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
            );
            if (!comment.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("작성자만 수정할 수 있습니다.");
            }

            comment.update(requestDto);
            return new CommentResponseDto(comment);
        }
        throw new APIException(ExceptionStatus.INVALID_TOKEN);
    }

    // 3. 댓글 삭제
    @Transactional
    public CommentDeleteDto deletecomment(Long Id, HttpServletRequest request) {
        String token = jwtUtil.getJwtFromHeader(request);
        Claims claims;

        //토큰 확인
        // 토큰 검증
        if (jwtUtil.validateToken(token)) {
            // 토큰에서 사용자 정보 가져오기
            claims = jwtUtil.getUserInfoFromToken(token);

            //토큰에 userId가 있는지
            // 토큰에서 가져온 사용자 정보를 사용하여 DB 조회 -> 로그인 안했으면 로그인 하라고 메시지
            User user = userRepository.findByUsername(claims.getSubject()).orElseThrow(
                    () -> new IllegalArgumentException("로그인 해주세요")
            );

            Comment comment = commentRepository.findById(Id).orElseThrow(
                    () -> new IllegalArgumentException("해당 댓글이 존재하지 않습니다.")
            );

            //권한 체크? -> 현재 게시물의 게시물 작성자 이름이랑 사용자 이름이랑 같으면 가능
            if (!comment.getUsername().equals(user.getUsername())) {
                throw new IllegalArgumentException("작성자만 삭제할 수 있습니다.");
            }

            //삭제
            commentRepository.deleteById(Id);
            return new CommentDeleteDto("댓글 삭제 성공", 400);
        }
        throw new APIException(ExceptionStatus.INVALID_TOKEN);
    }
}
