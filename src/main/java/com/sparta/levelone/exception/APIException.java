package com.sparta.levelone.exception;

import lombok.Getter;

@Getter
public class APIException extends RuntimeException {
    private String message;
    private Integer statusCode;


    public APIException(ExceptionStatus exceptionStatus) {
        this.message = exceptionStatus.getMessage();
        this.statusCode = exceptionStatus.getStatusCode();
    }
}
