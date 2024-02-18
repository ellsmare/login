package com.example.login.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {







    // infoEdit 페이지
    @GetMapping("/users/info-edit-form")
    public String infoEdit() {
        return "user/infoEditForm";
    }

    // info 페이지
    @GetMapping("/users/info-form")
    public String info() {
        return "user/infoForm";
    }

    // 로그아웃 페이지
    @GetMapping("/users/logout")
    public String logout() {
        return "index";
    }

    // 로그인 페이지
    @GetMapping("/auth/signin-form")
    public String signIn() {
        return "user/signInForm";
    }

    // 회원가입 페이지
    @GetMapping("/auth/signup-form")
    public String signUp() {
        return "user/signUpForm";
    }

}
