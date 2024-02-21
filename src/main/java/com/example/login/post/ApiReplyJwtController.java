package com.example.login.post;

import com.example.login.user.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/api")
public class ApiReplyJwtController {
    public final PostService postService;
    public final PostRepository postRepository;
    public final MemberRepository memberRepository;




    // 삭제

    //수정

    // 상세보기

    // save

    // 조회







}



/*
*   Cookie[] cookies = req.getCookies();
        System.out.println("MemberApiController post: cookies 호출" + cookies);

        if(cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTHORIZATION_HEADER)) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8"); // Encode 되어 넘어간 Value 다시 Decode
                    } catch (UnsupportedEncodingException e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }
* */

  /* Cookie cookie = new Cookie(AUTHORIZATION_HEADER, token); // Name-Value
        cookie.setPath("/");

        // Response 객체에 Cookie 추가
        res.addCookie(cookie);*/

// 웹에 데이터를 RETURN,
// userentity(오브젝트) to data(json) 변환 :: 메세지 컨버터
/*principal :: 접근주체*/