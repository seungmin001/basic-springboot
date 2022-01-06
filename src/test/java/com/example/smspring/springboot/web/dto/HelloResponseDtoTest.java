package com.example.smspring.springboot.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {
    @Test
    public void test_lombok() {
        //given
        String name = "test";
        int amount = 10000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        //then
        assertThat(dto.getName()).isEqualTo(name); // assertj의 검증 메소드 / assertThat(검증대상).isEqualTo 등 메소드 체이닝
        assertThat(dto.getAmount()).isEqualTo(amount); // assertj의 동등 비교 메소드 / 동일하지 않으면 fail
    }
}
