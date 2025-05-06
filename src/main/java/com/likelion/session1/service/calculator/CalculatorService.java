package com.likelion.session1.service.calculator;

import org.springframework.stereotype.Service;

// 비즈니스 로직을 처리하는 서비스 레이어

@Service
public class CalculatorService {

    public int add(int number1, int number2) {
        return number1 + number2;
    }

    public int multiply(int number1, int number2) {
        return number1 * number2;
    }

    public int subtract(int number1, int number2) { return number1 - number2; }
}
