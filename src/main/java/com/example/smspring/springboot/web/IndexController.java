package com.example.smspring.springboot.web;

import com.example.smspring.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) { // model : 서버 템플릿 엔진에서 사용할 수 있는 객체
        model.addAttribute("posts",postsService.findAllDesc()); // find결과를 index.mustache에 전달하도록
        return "index"; //mustache 스타터에 의해 "src/main/resources/templates"+return문자열+".mustache"(확장자) 로 전환되어 View Resolver가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
