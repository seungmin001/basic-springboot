package com.example.smspring.springboot.config.auth.dto;

import com.example.smspring.springboot.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    // 이 클래스에는 인증된 사용자 정보만 필요.
    // 이렇게 따로 만들지 않고 User 엔티티에 직렬화를 구현할 시, User와 관계있는 자식 엔티티까지 직렬화 대상이고 이는 성능 이슈(직렬화는 Reflection으로 인한 객체 생성으로 성능 이슈 존재), 부수효과가 발생.
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}