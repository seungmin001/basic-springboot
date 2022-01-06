package com.example.smspring.springboot.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter // 선언된 모든 필드의 getter 생성
@RequiredArgsConstructor // 선언된 모든 final 필드가 포함된 생성자 생성
public class HelloResponseDto {

    private final String name;
    private final int amount;
}
