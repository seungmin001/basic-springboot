package com.example.smspring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication // main class of project. 이 annotation 위치부터 설정을 읽어 항상 프로젝트 최상단에 클래스가 위치해야한다.
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //내장 WAS 실행
    }
}
