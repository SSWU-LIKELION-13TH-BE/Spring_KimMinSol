package com.likelion.session1.dto.calculator.request;

// POST 요청에서 두 개의 숫자를 받아오는 데이터 전송 객체

public class CalculatorMultiplyRequest {
    private final int number1;
    private final int number2;

    public CalculatorMultiplyRequest(int number1, int number2) {
        this.number1 = number1;
        this.number2 = number2;
    }

    public int getNumber1() {
        return number1;
    }

    public int getNumber2() {
        return number2;
    }
}
