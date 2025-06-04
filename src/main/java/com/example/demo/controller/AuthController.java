package com.example.demo.controller;

import com.example.demo.apiPayload.dto.ApiResponse;
import com.example.demo.apiPayload.code.SuccessStatus;
import com.example.demo.dto.SignupRequestDto;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<String> signup(@Valid @RequestBody SignupRequestDto request) {
        authService.signup(request);
        return ApiResponse.of(SuccessStatus._OK, "회원가입을 성공했습니다.");
    }
}
