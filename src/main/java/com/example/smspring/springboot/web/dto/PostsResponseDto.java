package com.example.smspring.springboot.web.dto;

import com.example.smspring.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity) { // entity 필드만 사용하므로 생성자 param으로 entity를 받아 필드에 값을 넣는다. 모든 필드가 포함된 생성자 대신 entity를 받는 것으로 처리 가능.
        this.id = entity.getId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.author = entity.getAuthor();
    }
}
