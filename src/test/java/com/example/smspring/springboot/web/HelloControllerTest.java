package com.example.smspring.springboot.web;

import com.example.smspring.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //spring boot test - junit 연결. junit에 내장된 실행자가 아닌 springrunner 실행자도 사용한다
@WebMvcTest(controllers = HelloController.class,
excludeFilters = { // SecurityConfig를 읽어도 그를 생성하기 위해 필요한 CustomOAuth2UserService를 읽을 수 없어 스캔 대상에서 SecurityConfig 제거
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE,
        classes = SecurityConfig.class)
})
// Spring MVC에만 집중. @Controller,@ControllerAdvice 사용가능. @Repository, @Service, @Component 사용불가
public class HelloControllerTest {

    @Autowired //스프링 Bean 주입받기
    private MockMvc mvc; //웹 API 테스트 시 사용

    @Test
    @WithMockUser(roles="USER") // 인증된 가짜 사용자 사용
    public void returned_hello() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc을 통해 /hello 주소로 GET요청 실행. chaining 가능
                .andExpect(status().isOk()) // perform 결과 검증. status Ok(200) 코드인지 검증
                .andExpect(content().string(hello)); // response 본문 내용 검증 as a string
    }

    @Test
    @WithMockUser(roles="USER")
    public void returned_helloDto() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // json 응담값을 필드별로 검증하는 메소드
                .andExpect(jsonPath("$.amount", is(amount))); // $ 을 기준으로 필드명 명시, hamcrest Matchers를 이용해 expected value 작성
    }
}
