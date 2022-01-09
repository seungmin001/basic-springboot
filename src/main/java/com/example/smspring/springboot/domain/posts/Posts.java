package com.example.smspring.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter //lombok
@NoArgsConstructor //lombok 기본생성자 자동 추가
@Entity //Table posts table로 match
public class Posts {

    @Id //PK
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
    private Long id;

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    //Column 선언하지 않아도 필드는 모두 칼럼임. 기본값 외에 변경 옵션을 위해 annotation 씀
    private String author;

    @Builder //lombok 해당 클래스의 빌더 패턴 클래스를 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함(필수필드라고 보면..)
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    //Entity 클래스에는 절대 Setter를 추가하지 않는다! 필드 값 변경이 필요하다면 목적이 명확한 메소드를 추가해야한다.
    //생성자(Builder)를 통해 최종값으로 채운 후 DB에 insert, 값 변경 시 public 메소드이용.
}
