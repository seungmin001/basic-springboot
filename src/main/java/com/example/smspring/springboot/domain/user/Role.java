package com.example.smspring.springboot.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role { // 각 사용자의 권한을 관리할 enum 클래스

    // Enumerated Value에서 소괄호를 통해 생성자에 인자를 전달할 수 있음.
    // Spring Security 에서는 권한 코드에 항상 ROLE_이 앞에 있어야함
    GUEST("ROLE_GUEST","손님"), 
    USER("ROLE_USER","일반 사용자"); 

    private final String key;
    private final String title;
}
