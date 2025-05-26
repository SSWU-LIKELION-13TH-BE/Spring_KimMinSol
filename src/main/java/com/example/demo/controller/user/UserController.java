package com.example.demo.controller.user;

import com.example.demo.dto.user.request.UserLoginRequestDto;
import com.example.demo.dto.user.request.UserSignupRequestDto;
import com.example.demo.dto.user.response.UserLoginResponseDto;
import com.example.demo.entity.User.User;
import com.example.demo.service.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody UserSignupRequestDto requestDto) {
        userService.signup(requestDto);
        return ResponseEntity.ok("회원가입 성공!");
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> login(@RequestBody UserLoginRequestDto requestDto) {
        UserLoginResponseDto response = userService.login(requestDto);
        return ResponseEntity.ok(response);
     }

    @GetMapping("/me")
    public ResponseEntity<Map<String, String>> getMyInfo(Authentication authentication) {
        String userId = authentication.getName();
        User user = userService.findByUserId(userId);

        Map<String, String> response = new HashMap<>();
        response.put("userId", user.getUserId());
        response.put("name", user.getName());
        response.put("profileImage", user.getProfileImage());

        return ResponseEntity.ok(response);
    }
}
