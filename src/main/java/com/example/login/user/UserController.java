package com.example.login.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {



    // 회원 관련 정보 받기



    // infoEdit 페이지
    @GetMapping("/user/infoEdit-page")
    public String infoEdit() {
        return "user/infoEdit";
    }

    // info 페이지
    @GetMapping("/user/info-page")
    public String info() {
        return "user/info";
    }

    // 로그아웃 페이지
    @GetMapping("/user/dologout")
    public String logout() {
        return "redirect:/";
    }

    // 로그인 페이지
    @GetMapping("/auth/signIn-page")
    public String signIn() {
        return "user/signIn";
    }

    // 회원가입 페이지
    @GetMapping("/auth/signUp-page")
    public String signUp() {
        return "user/signUp";
    }

}
