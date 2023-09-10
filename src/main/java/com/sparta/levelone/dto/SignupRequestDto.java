package com.sparta.levelone.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto {
    @NotBlank(message = "아이디는 필수 입력 값입니다.")
    @Size(min = 4, max = 10, message = "사용자 이름은 4자 이상 10자 이하이어야 합니다.")
    @Pattern(regexp = "^[a-z0-9]{4,10}$", message = "아이디 4~10자 영문 소문자, 숫자를 사용하세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Size(min = 8, max = 15, message = "비밀번호는 8자 이상 15자 이하이어야 합니다.")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,15}$", message = "비밀번호는 8~15자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    private boolean admin = false;
    private String adminToken = "";
}

