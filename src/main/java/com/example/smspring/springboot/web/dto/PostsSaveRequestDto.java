package com.example.smspring.springboot.web.dto;

import com.example.smspring.springboot.domain.posts.Posts;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PostsSaveRequestDto {

    private String title;
    private String content;
    private String author;

    @Builder
    public PostsSaveRequestDto(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public Posts toEntity() {
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }

    // Entity(Posts)와 비슷함에도 사용하는 이유 : entity는 db와 밀접하므로 화면 변경 같은 사소한 변경 때문에 스키마를 변경할 수 없음.
    // 따라서 자주 변경해도 괜찮게 Request/Response용 Dto를 View를 위한 클래스로 사용함. --> View Layer/DB Layer의 역할 분리

}
