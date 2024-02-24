package com.example.login.post;

import com.example.login.auth.UserDetailsImpl;
import com.example.login.user.MemberRepository;
import com.example.login.user.ResponseDto;
import com.example.login.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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




    // save
/*
    @PostMapping("/users/posts/{id}")
    public ResponseDto<String> posts(@RequestBody ReplyRequestDto replyRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("******posts 게시글 저장*********");

        // 로그인 확인
        UserEntity user = userDetails.getUser();

        // postRequestDTO 저장
        postService.savePost(postRequestDto, user);

        // 반환 정보 추가 :: 팔로우, 좋아요, 댓글
        log.info("******posts 게시글 저장*********");
        return new ResponseDto<>(HttpStatus.OK.value(), "글쓰기 성공");
    }

*/










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