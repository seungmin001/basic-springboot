package com.example.smspring.springboot.web;

import com.example.smspring.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //controller가 JSON을 반환 (@ResponseBody 대체)
public class HelloController {

    @GetMapping("/hello") //HTTP Get request 처리 API
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(
            @RequestParam("name") String name, // 외부에서 API로 name이란 이름으로 넘긴 파라미터를 가져와 메소드 파라미터에 저장한다.
            @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
