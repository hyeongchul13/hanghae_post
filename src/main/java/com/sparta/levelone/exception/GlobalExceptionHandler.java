package com.sparta.levelone.exception;

import com.sparta.levelone.dto.ExceptionResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({IllegalArgumentException.class})
    public ResponseEntity<ExceptionResponseDto> illegalArgumentExceptionHandler(
            IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(new ExceptionResponseDto(e.getMessage(), 400));
    }

    @ExceptionHandler({APIException.class})
    public ResponseEntity<ExceptionResponseDto> APIExceptionHandler(
            APIException e) {

        return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage(), e.getStatusCode()), HttpStatus.valueOf(e.getStatusCode()));
//        return new ResponseEntity<>(new ExceptionResponseDto(e.getMessage(), e.getStatusCode()), HttpStatus.OK);
    }
}