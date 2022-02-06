package com.example.smspring.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

// @EnableJpaAuditing // JPA Auditing 활성화 // JPA metamodel must not be empty! @Entity가 하나 이상 필요한 어노테이션인데 WebMvcTest를 이용하므로 이를 읽을 수 없어 따로 JpaConfig로 이동
@SpringBootApplication // main class of project. 이 annotation 위치부터 설정을 읽어 항상 프로젝트 최상단에 클래스가 위치해야한다.
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args); //내장 WAS 실행
    }
}
