package com.example.login.post;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class PostController {
    // 게시판 상세 페이지(수정,삭제)
    @GetMapping("/users/postDetail-page")
    public String postDetail() {
        return "users/post/postDetail";
    }


    // 게시판 글쓰기 페이지(에디터 폼) user권한필요
    @GetMapping("/users/postEdit-page")
    public String posts() {
        return "users/post/postEdit";
    }


    // 게시판 목록 페이지
    @GetMapping("/auth/postList-page")
    public String postList() {
        return "users/post/postList";
    }

}
