package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class SignupRequestDto {

    @NotBlank(message = "아이디는 필수입니다.")
    @Size(min = 2, message = "아이디는 최소 2자 이상 작성해주세요.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    @Size(min = 8, message = "비밀번호는 최소 8자 이상 작성해주세요.")
    private String password;

    @NotBlank(message = "이름은 필수입니다.")
    private String name;
}
