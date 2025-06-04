package com.example.demo.service;

import com.example.demo.apiPayload.code.ErrorStatus;
import com.example.demo.apiPayload.exception.GeneralException;
import com.example.demo.dto.LoginRequestDto;
import com.example.demo.dto.LoginResponseDto;
import com.example.demo.dto.PasswordChangeRequestDto;
import com.example.demo.dto.SignupRequestDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserJpaRepository;
import com.example.demo.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserJpaRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public void signup(SignupRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new GeneralException(ErrorStatus.USERNAME_ALREADY_EXISTS);
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .name(request.getName())
                .build();

        userRepository.save(user);
    }

    public LoginResponseDto login(LoginRequestDto request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new GeneralException(ErrorStatus.PASSWORD_MISMATCH)); // 또는 USER_NOT_FOUND 추가

        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new GeneralException(ErrorStatus.PASSWORD_MISMATCH);
        }

        String token = jwtTokenProvider.createToken(user.getUsername()); // 👈 아래에 유틸 추가 예정
        return new LoginResponseDto(token);
    }

    public void changePassword(User user, PasswordChangeRequestDto request) {

        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new GeneralException(ErrorStatus.PASSWORD_MISMATCH);
        }

        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new GeneralException(ErrorStatus.PASSWORD_CONFIRM_MISMATCH);
        }

        user.updatePassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }
}
