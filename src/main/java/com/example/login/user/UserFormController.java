package com.example.login.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class UserFormController {
    public final MemberRepository memberRepository;


    // infoEdit 페이지
    @GetMapping("/users/info-edit-form")
    public String infoEdit() {
        return "user/profleEditForm";
    }


    // info 페이지
    @GetMapping("/users/info-form")
    public String info(Model model) {
        model.addAttribute("users", memberRepository.findAll());  //findAll
        return "user/infoForm";
    }

    // 로그인 페이지
    @GetMapping("/auth/login-form")
    public String login() {
        return "user/loginForm";
    }


    // 회원가입 페이지
    @GetMapping("/auth/signup-form")
    public String signUp() {
        return "user/signUpForm";
    }

}
