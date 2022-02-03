package com.example.smspring.springboot.config;

import com.example.smspring.springboot.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration
public class WebConfig implements WebMvcConfigurer { // LoginUserArgumentResolver가 스프링에서 인식할 수 있도록
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        // HandlerMethodArgumentResolver는 항상 이 함수를 통해 추가해야함
        argumentResolvers.add(loginUserArgumentResolver);
    }
}
