package com.example.login.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1")
public class UserController {
    public final MemberRepository memberRepository;


    // infoEdit 페이지
    @GetMapping("/users/profile")
    public String infoEdit() {
        return "user/profileEditForm";
    }


    // info 페이지
    @GetMapping("/users/info")
    public String info(Model model) {
        model.addAttribute("users", memberRepository.findAll());  //findAll
        return "user/infoForm";
    }

    // 로그인 페이지
    @GetMapping("/auth/login-page")
    public String login() {
        return "user/loginForm";
    }


    // 회원가입 페이지
    @GetMapping("/auth/signup")
    public String signUp() {
        return "user/signUpForm";
    }

}
