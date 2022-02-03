package com.example.smspring.springboot.domain.user;

import com.example.smspring.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity { // 사용자 정보 담당할 도메인

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING) //JPA로 DB에 저장할 때의 형식 지정
    @Column(nullable = false)
    private Role role;
    
    @Builder
    public User(String name, String email, String picture, Role role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }
    
    public User update(String name, String picture){
        this.name=name;
        this.picture=picture;
        
        return this;
    }
    
    public String getRoleKey(){
        return this.role.getKey();
    }
}
