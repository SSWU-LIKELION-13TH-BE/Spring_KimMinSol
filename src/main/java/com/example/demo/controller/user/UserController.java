package com.example.demo.controller.user;

import com.example.demo.apiPayload.dto.ApiResponse;
import com.example.demo.apiPayload.code.SuccessStatus;
import com.example.demo.dto.PasswordChangeRequestDto;
import com.example.demo.entity.User;
import com.example.demo.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final AuthService authService;

    @PatchMapping("/password")
    public ApiResponse<String> changePassword(
            @AuthenticationPrincipal User user,
            @Valid @RequestBody PasswordChangeRequestDto request
    ) {
        authService.changePassword(user, request);
        return ApiResponse.of(SuccessStatus._OK, "비밀번호가 변경되었습니다.");
    }
}
