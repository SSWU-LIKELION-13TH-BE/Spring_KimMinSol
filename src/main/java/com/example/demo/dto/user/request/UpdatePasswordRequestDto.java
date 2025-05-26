package com.example.demo.dto.user.request;

import lombok.Data;

@Data
public class UpdatePasswordRequestDto {
    private String currentPassword;
    private String newPassword;
    private String confirmPassword;
}
