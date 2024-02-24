package com.example.login.user;

import com.example.login.auth.UserDetailsImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1")
public class UserController {
    public final MemberRepository memberRepository;
    public final MemberService memberService;


    // profile 페이지 :: 본인만 확인  (메인페이지 )
    @GetMapping("/users/profile")
    public String profile(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {

        UserEntity user = userDetails.getUser();

        // 반환 정보 추가 :: 팔로우, 좋아요, 댓글
        UserEntity info = UserEntity.builder()
                .nickname(user.getNickname())
                .infoText(user.getInfoText())
                .email(user.getEmail())
                .username(user.getUsername())
                .build();
        // 반환 정보 추가 :: 팔로우, 좋아요, 댓글

        model.addAttribute("info", info);
        System.out.println(user);
        return "user/profileEditForm";
    }


    // info 페이지 :: 본인만 확인
    @GetMapping("/users/info")
    public String info(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
        System.out.println(userDetails);
        UserEntity user = userDetails.getUser();

        // 추가 :: 팔로우, 좋아요, 댓글
        UserEntity info = UserEntity.builder()
                .nickname(user.getNickname())
                .infoText(user.getInfoText())
                .email(user.getEmail())
                .password(user.getPassword())
                .username(user.getUsername())
                .build();
        model.addAttribute("info", info);
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
