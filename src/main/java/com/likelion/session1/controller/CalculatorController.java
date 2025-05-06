package com.likelion.session1.controller;

import com.likelion.session1.dto.calculator.request.CalculatorAddRequest;
import com.likelion.session1.dto.calculator.request.CalculatorMultiplyRequest;
import com.likelion.session1.service.calculator.CalculatorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/calculator")
public class CalculatorController {
    /*
    // GET 방식 덧셈 API
    // /add?number1=5&number2=10
    @GetMapping("/add")
    public int addTwoNumbers(
            @RequestParam int number1,
            @RequestParam int number2
    ) {
        return number1 + number2;
    }

    // POST 방식 곱셈 API
    // 요청: POST/multiply
    // 바디: {"number1" : 3, "number2" : 4}

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return request.getNumber1() * request.getNumber2();
    }

    // DTO 방식 덧셈 API
    // /add?number1=5&number2=10
    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return request.getNumber1() + request.getNumber2();
    }
     */
    private final CalculatorService calculatorService;

    public CalculatorController(CalculatorService calculatorService) {
        this.calculatorService = calculatorService;
    }

    @GetMapping("/add")
    public int addTwoNumbers(CalculatorAddRequest request) {
        return calculatorService.add(request.getNumber1(), request.getNumber2());
    }

    @PostMapping("/multiply")
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiplyRequest request) {
        return calculatorService.multiply(request.getNumber1(), request.getNumber2());
    }
}