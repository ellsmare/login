package com.example.login.user;

import com.example.login.auth.JwtUtil;
import com.example.login.auth.UserDetailsImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;

import static com.example.login.auth.JwtUtil.AUTHORIZATION_HEADER;

@RequiredArgsConstructor
@RestController
//@RequestMapping("/api")
public class ApiUserJwtController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    // findUsername 찾기  /find-username
    @PostMapping("/test/users/find-username")
    public ResponseDto<String> findUsername(@RequestParam("email") String email) {
        //System.out.println(email);
        String username = memberService.findUsernameByEmail(email);

        if (username == null) {
            System.out.println("findByUsername 실패했습니다:: email 불일치");
            throw new IllegalArgumentException("멤버 조회 실패");
        }

        ResponseDto response = new ResponseDto<>(HttpStatus.OK.value(), "성공");
        return response;
    }


    //pageList -userEntity 페이징
    //    public List<UserEntity> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
//
//        Page<UserEntity> pageUser = memberRepository.findAll(pageable);
//        System.out.println(pageUser);
////        if(pageUser.isLast()){
////            System.out.println("pageUser");
////        }
//        List<UserEntity> user = pageUser.getContent();
//        return user;
//    }


//    public UserEntity detail(@PathVariable long idx){
//
//        memberService.update(idx);
//
//        System.out.println("detail : " + userEntity);
//        if(userEntity==null) {
//            System.out.println("getInfo 실패했습니다:: response null");
//            throw new IllegalArgumentException("로그인이 필요합니다.");
//        };
//        return
//    }


    //findById - 내 정보 수정  update
/*    @PostMapping("/test/users/{idx}")
    public ResponseDto<String> updateInfo(InfoRequestDto infoRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {

        System.out.println("MemberApiController updateInfo:  호출" + infoRequestDto.getPassword());
        System.out.println("MemberApiController updateInfo:  호출" + infoRequestDto.getNickname());
//        System.out.println("MemberApiController updateInfo:  호출" + infoRequestDto.getImg());

        UserEntity userEntity = userDetails.getUserEntity();
        System.out.println("save post ::" + userEntity);

//        memberService.update(userDetails);


        new ResponseDto<>(HttpStatus.OK.value(), "성공");
    }*/



    /* 로그아웃  :: 시큐리티 + 프론트단 쿠키 삭제   todo 보통 토큰 만료 */

    /*로그인   :: 주석 경로가 겹치면 에러 */

    /*회원가입  */
    @PostMapping("/auth/signup")
    public ResponseDto<String> register(@Valid @RequestBody RegisterRequestDto registerRequestDTO) {
        //System.out.println("MemberApiController post: save 호출" + registerRequestDTO.getUsername());
        //System.out.println("MemberApiController post: save 호출" + registerRequestDTO.getPassword());
        //System.out.println("MemberApiController post: save 호출" + registerRequestDTO.getEmail());

        memberService.register(registerRequestDTO);
        System.out.println("memberService register 통과");

        //System.out.println("memberService 회원가입성공 :: "+response);
        return new ResponseDto<>(HttpStatus.OK.value(), "회원가입성공");  //(HttpStatus status,data)
    }

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