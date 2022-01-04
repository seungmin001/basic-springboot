package com.example.smspring.springboot.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //controller가 JSON을 반환 (@ResponseBody 대체)
public class HelloController {

    @GetMapping("/hello") //HTTP Get request 처리 API
    public String hello(){
        return "hello";
    }
}
