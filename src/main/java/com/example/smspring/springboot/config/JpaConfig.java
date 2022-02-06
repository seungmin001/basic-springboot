package com.example.smspring.springboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing // JPA Auditing (자동 생성시간, 수정시간 등 저장) 활성화
public class JpaConfig {
}
