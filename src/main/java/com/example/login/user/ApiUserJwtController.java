package com.example.login.user;

import com.example.login.auth.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Slf4j(topic = "ApiUserJwtController")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ApiUserJwtController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    @GetMapping("/getUsername")
    public String getUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        //model.addAttribute("haha", "타임리프환결설정");
        log.info("getUser :: " + userDetails);

        UserEntity userEntity =  userDetails.getUser();
        log.info("userDetails.getUser :: " + userEntity);

        return "redirect:/";
    }


    // findUsername 찾기  /find-username  post??? todo
    @PostMapping("/test/users/find-username")
    public ResponseDto<String> findUsername(@RequestParam("email") String email) {
        //System.out.println(email);
        String username = memberService.findUsernameByEmail(email);

        if (username == null) {
            System.out.println("findByUsername 실패했습니다:: email 불일치");
            throw new IllegalArgumentException("멤버 조회 실패");
        }

        return new ResponseDto<>(HttpStatus.OK.value(), "성공");
    }

/*
    pageList -userEntity 페이징
        public List<UserEntity> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        Page<UserEntity> pageUser = memberRepository.findAll(pageable);
        System.out.println(pageUser);
//        if(pageUser.isLast()){
//            System.out.println("pageUser");
//        }
        List<UserEntity> user = pageUser.getContent();
        return user;
    }
*/
    
/*
    // 계정삭제  detail
    public UserEntity detail(@PathVariable long idx){

        memberService.update(idx);

        System.out.println("detail : " + userEntity);
        if(userEntity==null) {
            System.out.println("getInfo 실패했습니다:: response null");
            throw new IllegalArgumentException("로그인이 필요합니다.");
        };
        return
    }*/


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
    ///api/v1/users/logout
    @PostMapping("/users/logout")
    public ResponseDto<String> logout(){


        return new ResponseDto<>(HttpStatus.OK.value(), "logout 성공");  //(HttpStatus status,data)
    }


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
