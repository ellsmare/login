package com.example.login;

import com.example.login.auth.UserDetailsImpl;
import com.example.login.user.UserEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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
        return "index";
    }

    // 오류 페이지
    @GetMapping("/auth/error-form")
    public String error() {
        return "common/page/error";
    }


    // test
    @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        //model.addAttribute("haha", "타임리프환결설정");

        return "index";
    }


}
