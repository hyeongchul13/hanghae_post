package com.sparta.levelone.dto;

import lombok.Getter;

@Getter
public class ExceptionResponseDto {
    private String msg;
    private int statusCode;

    public ExceptionResponseDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
