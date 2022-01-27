package com.example.smspring.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "index"; //mustache 스타터에 의해 "src/main/resources/templates"+return문자열+".mustache"(확장자) 로 전환되어 View Resolver가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave(){
        return "posts-save";
    }
}
