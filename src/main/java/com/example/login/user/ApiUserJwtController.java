package com.example.login.user;

import com.example.login.auth.UserDetailsImpl;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static com.example.login.auth.JwtUtil.AUTHORIZATION_HEADER;

@Slf4j(topic = "ApiUserJwtController")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ApiUserJwtController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;


/*    @GetMapping("/getUsername")
    public String nowUser(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        //model.addAttribute("haha", "타임리프환결설정");
        log.info("getUser :: " + userDetails);

        UserEntity userEntity = userDetails.getUser();
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
    }*/


    /***************************************************** 기본  */


/*
    // 계정삭제  dropPost    -계정삭제-게시물 여부 확인
     @PostMapping("/users/{id}")
    public UserEntity dropPost (@PathVariable long id, @AuthenticationPrincipal UserDetailsImpl userDetails)throws Exception{
     log.info("**** dropPost 시작 *****");

     // request 인증 코드 포함,  true 이면 삭제 가능

          //사용자 확인
          if (userDetails != null) {
                    UserEntity userDetail = memberRepository.findByEmail(userDetails.getUsername())
                            .orElseThrow(() -> new UsernameNotFoundException("사용자를 확인할 수 없습니다."));


             //서비스
        memberService.update(id);



         return new ResponseDto<>(HttpStatus.OK.value(), "업데이트 성공");
    }*/

/*

    //findById - 내 정보 수정  update
    @PutMapping("/users/{id}")
    public ResponseDto<String> updateInfo(@PathVariable Long id, @RequestBody InfoRequestDto infoRequestDto,
                                        UserEntity userDetail) throws Exception {
        log.info("**** updateInfo 시작 *****");

         if (userDetails != null) {
            UserEntity userDetail = memberRepository.findByEmail(userDetails.getUsername())
                    .orElseThrow(() -> new UsernameNotFoundException("사용자를 확인할 수 없습니다."));

        if(id == userEntity.getId()){
            memberService.update(infoRequestDto);
            log.info("**** updateInfo service 통과 *****");
            return new ResponseDto<>(HttpStatus.OK.value(), "업데이트 성공");
        }

        return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), "업데이트 실패");

    }
*/



     // 로그아웃  :: 시큐리티 + 프론트단 쿠키 삭제 구현
    /*로그인   :: 경로가 겹치면 에러 */

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





    /*
    pageList -userEntity admin 계정 요소
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


}
