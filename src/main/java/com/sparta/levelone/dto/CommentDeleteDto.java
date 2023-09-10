package com.sparta.levelone.dto;

import lombok.Getter;

@Getter
public class CommentDeleteDto {
    private String msg;
    private int statusCode;

    public CommentDeleteDto(String msg, int statusCode) {
        this.msg = msg;
        this.statusCode = statusCode;
    }
}
