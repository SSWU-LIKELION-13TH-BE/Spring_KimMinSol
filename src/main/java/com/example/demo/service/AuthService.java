package com.example.demo.service;

import com.example.demo.apiPayload.code.ErrorStatus;
import com.example.demo.apiPayload.exception.GeneralException;
import com.example.demo.dto.SignupRequestDto;
import com.example.demo.entity.User;
import com.example.demo.repository.UserJpaRepository;
import com.example.demo.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserJpaRepository userRepository;

    public void signup(SignupRequestDto request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new GeneralException(ErrorStatus.USERNAME_ALREADY_EXISTS);
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword())
                .name(request.getName())
                .build();

        userRepository.save(user);
    }
}
