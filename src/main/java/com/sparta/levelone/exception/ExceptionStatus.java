package com.sparta.levelone.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ExceptionStatus {

    NOT_FOUND_POST_ID("해당 게시물을 찾을 수 없습니다.", 400),
    INVALID_TOKEN("토큰이 유효하지 않습니다.", 400),
    NOT_FOUND_USER("회원을 찾을수 없습니다.",400),
    DUPICATED_USER("중복된 username입니다.",400);



    private String message;
    private Integer statusCode;


}

