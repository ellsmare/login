package com.example.login.common;

import com.example.login.auth.UserDetailsImpl;
import com.example.login.user.MemberService;
import com.example.login.user.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1")
public class MainController {

    private final MemberService memberService;




    // 메인 페이지
    @GetMapping( {"","/"})
    public String index(Model model) {

        //UserEntity user = userDetails.getUser();
        //model.addAttribute("info", memberService.getProfile(user.getId()));

        //return "user/profileEditForm";
        return "index";
    }


    /*    @GetMapping("/getUsername")
    public String nowUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        //model.addAttribute("haha", "타임리프환결설정");
        log.info("getUser :: " + userDetails);

        UserEntity userEntity = userDetails.getUser();
        log.info("userDetails.getUser :: " + userEntity);

        return "redirect:/";
    }


    // 오류 페이지
    @GetMapping("/auth/error")
    public String error() {
        return "common/page/error";
    }


    // test
 /*   @GetMapping("/thymeleaf")
    public String thymeleaf(Model model) {
        //model.addAttribute("haha", "타임리프환결설정");

        return "index";
    }*/


}
