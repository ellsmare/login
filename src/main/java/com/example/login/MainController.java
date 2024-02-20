package com.example.login;

import com.example.login.auth.UserDetailsImpl;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class MainController {




    // 메인 페이지
    @GetMapping({"", "/"})
    public String index() {
        //  model.addAttribute("username", "username");
        return "/index";
    }


    // 오류 페이지
    @GetMapping("/auth/error-form")
    public String error() {
        return "common/page/error";
    }

    //test
    @GetMapping("/test")
    public String test() {
        return "타임리프 확인";
    }

    // test
    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        model.addAttribute("haha", "타임리프환결설정");
        return "index";
    }
}
