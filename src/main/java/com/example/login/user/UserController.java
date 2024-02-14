package com.example.login.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {



    // 회원 관련 정보 받기



    // infoEdit 페이지
    @GetMapping("/users/infoEdit-page")
    public String infoEdit() {
        return "users/infoEdit";
    }

    // info 페이지
    @GetMapping("/users/info-page")
    public String info() {
        return "users/info";
    }

    // 로그인 페이지
    @GetMapping("/auth/signIn-page")
    public String signIn() {
        return "users/signIn";
    }

    // 회원가입 페이지
    @GetMapping("/auth/signUp-page")
    public String signUp() {
        return "users/signUp";
    }

}
