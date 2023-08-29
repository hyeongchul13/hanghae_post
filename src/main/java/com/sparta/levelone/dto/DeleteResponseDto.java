package com.sparta.levelone.dto;

import lombok.Getter;

@Getter
public class DeleteResponseDto {
    private String response;

    public DeleteResponseDto(String response){
        this.response = response;
    }
}
