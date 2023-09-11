package com.sparta.levelone.service;


import com.sparta.levelone.dto.LoginRequestDto;
import com.sparta.levelone.dto.SignupRequestDto;
import com.sparta.levelone.entity.User;
import com.sparta.levelone.entity.UserRoleEnum;
import com.sparta.levelone.exception.APIException;
import com.sparta.levelone.exception.ExceptionStatus;
import com.sparta.levelone.jwt.JwtUtil;
import com.sparta.levelone.repository.UserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    // 관리자 회원 가입 인가 방법  -- ADMIN_TOKEN 선언
    private static final String ADMIN_TOKEN = "AAABnvxRVklrnYxKZ0aHgTBcXukeZygoC";

    // 회원가입
    @Transactional
    public void signup(SignupRequestDto signupRequestDto) {
        String username = signupRequestDto.getUsername();
        String password = passwordEncoder.encode(signupRequestDto.getPassword());

        // 회원 중복 확인
        Optional<User> found = userRepository.findByUsername(username);
        if (found.isPresent()) {
            throw new APIException(ExceptionStatus.DUPICATED_USER);
        }

        // 사용자 ROLE 확인 (관리자인 경우, ADMIN / 사옹자인 경우, USER 부여)
        UserRoleEnum role = UserRoleEnum.USER;
        if (signupRequestDto.isAdmin()) {
            if (!signupRequestDto.getAdminToken().equals(ADMIN_TOKEN)) {
                throw new IllegalArgumentException("관리자 암호가 틀려 등록이 불가능합니다.");
            }
            role = UserRoleEnum.ADMIN;
        }
        //DTO -> ENTITY signUpRequestDto -> USER
        User user = new User(username, password, role);
        userRepository.save(user);
    }


    // 로그인
    @Transactional(readOnly = true)
    public void login(LoginRequestDto loginRequestDto, HttpServletResponse response) {
        String username = loginRequestDto.getUsername();
        String password = passwordEncoder.encode(loginRequestDto.getPassword());

        // 사용자 확인
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new APIException(ExceptionStatus.NOT_FOUND_USER)
        );
        // 비밀번호 확인
        if (!user.getPassword().equals(password)) {
            throw new APIException(ExceptionStatus.NOT_FOUND_USER);
        }
        // 토큰을 쿠키에 담아서 보내는 방법과 responses header에 담아서 보내는 방법이 있는데, 여기선 response header에 담아서 보냄
        response.addHeader(JwtUtil.AUTHORIZATION_HEADER, jwtUtil.createToken(user.getUsername(), user.getRole()));
    }
}
