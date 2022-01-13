package com.example.smspring.springboot.web;

import com.example.smspring.springboot.domain.posts.Posts;
import com.example.smspring.springboot.domain.posts.PostsRepository;
import com.example.smspring.springboot.web.dto.PostsSaveRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
// JPA 기능까지 테스트 할 땐 이 annotation & TestRestTemplate 사용
public class PostsApiControllerTest {

    @LocalServerPort // runtime에 할당되는 HTTP Port를 주입
    private int port;

    @Autowired //bean의 생성 직후에 주입됨
    private TestRestTemplate restTemplate;

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void tearDown() throws Exception {
        postsRepository.deleteAll();
    }

    @Test
    public void checkUploadPosts() {
        //given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build(); //controller -> service

        String url = "http://localhost:" + port + "/api/v1/posts";


        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);  // url, request object, response Type Class

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L); //PostsService에서 id 반환함

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title); // 로그 보니 get할 때마다 repository 접근하는 듯
        assertThat(all.get(0).getContent()).isEqualTo(content);

    }
}
