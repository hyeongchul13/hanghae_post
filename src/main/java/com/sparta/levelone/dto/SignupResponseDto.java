package com.sparta.levelone.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupResponseDto {
    private String msg;
    private int statusCode;

    public SignupResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }

    public SignupResponseDto(String defaultMessage) {
        this.msg = defaultMessage;
    }
}
