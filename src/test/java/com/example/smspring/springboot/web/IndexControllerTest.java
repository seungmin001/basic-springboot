package com.example.smspring.springboot.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT)
public class IndexControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void loadingMainPage() {
        //when
        String body = this.restTemplate.getForObject("/", String.class); //testRestTemplate 통해 "/" 경로 Get 접근하고 response를 지정한 class로 변환하며 반환.

        //then
        assertThat(body).contains("스프링 부트로 시작하는 웹 서비스");
    }
}
