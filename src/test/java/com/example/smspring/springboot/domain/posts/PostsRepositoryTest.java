package com.example.smspring.springboot.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest //별다른 설정 없으면 자동 h2 db 실행
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After // Junit에서 unit test가 끝날 때마다 수행됨
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void saveAndView() {
        //given
        // create
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save( // table posts에 insert/update(id x/o) query 실행.
                Posts.builder()
                        .title(title)
                        .content(content)
                        .author("minnie9808@gmail.com")
                        .build());

        //when
        // 모든 게시글 read
        List<Posts> postsList = postsRepository.findAll(); //posts의 모든 데이터 조회

        //then
        Posts post = postsList.get(0);
        assertThat(post.getTitle()).isEqualTo(title);
        assertThat(post.getContent()).isEqualTo(content);
    }
}
