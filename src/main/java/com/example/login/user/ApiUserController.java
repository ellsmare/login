package com.example.login.user;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
@RestController
public class ApiUserController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;


    // findUsername 찾기
    @PostMapping("/test/user/findUsername")
    public  ResponseDTO<String> findUsername(@RequestParam("email") String email) {
        //System.out.println(email);
        String username = memberService.findUsernameByEmail(email);

        if(username==null){
            System.out.println("findByUsername 실패했습니다:: email 불일치");
            throw new IllegalArgumentException("멤버 조회 실패");
        }

        ResponseDTO response = new ResponseDTO<>(HttpStatus.OK.value(), "성공");
        return response;
    }

    //findAll -userEntity :: admin??
    @GetMapping("/test/user")
    public List<UserEntity> list(){
        return memberRepository.findAll();
    }

    //pageList -userEntity 페이징 :: 또 안됨,,, 삭제해서 다시 작성했는데 또 안됨.
  /*  @GetMapping("/test/user/page")
    public List<UserEntity> pageList(@PageableDefault(size = 2, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){

        Page<UserEntity> pageUser = memberRepository.findAll(pageable);
        System.out.println(pageUser);
//        if(pageUser.isLast()){
//            System.out.println("pageUser");
//        }
        List<UserEntity> user = pageUser.getContent();
        return user;
    }*/

//
//
//    public UserEntity detail(@PathVariable long idx){
//
//        memberService.update(idx);
//
//
//
//        System.out.println("detail : " + userEntity);
//        if(userEntity==null) {
//            System.out.println("getInfo 실패했습니다:: response null");
//            throw new IllegalArgumentException("로그인이 필요합니다.");
//        };
//        return
//    }

    //findById - 내 정보 수정
   /* @PostMapping("/test/user/{idx}")
    public ResponseDTO<String> saveInfo(@PathVariable long idx) throws Exception {
        UserEntity userEntity  = memberService.getInfo(idx);
        ResponseDTO response = new ResponseDTO<>(HttpStatus.OK.value(), "성공");
        return userEntity;
    }
*/


    //getInfo - 내정보조회
    @GetMapping("/users/info")
    public UserEntity getInfo(@PathVariable long idx) throws Exception {
        UserEntity userEntity  = memberService.getInfo(idx);
       // ResponseDTO response = new ResponseDTO<>(HttpStatus.OK.value(), "성공");
        return userEntity;
    }


    //logout :: 모든 session 삭제
    @GetMapping("/users/logout")
    public ResponseDTO<String> logout(HttpSession session){
        session.invalidate(); //
        ResponseDTO response = new ResponseDTO<>(HttpStatus.OK.value(), "성공");
        return response;
    }

    /* 로그인 - session */
    /*principal :: 접근주체*/
    @PostMapping("/auth/signIn")
    public ResponseDTO<String> signIn(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) throws Exception{
        System.out.println("MemberApiController post: login 호출" + loginRequestDto);

        UserEntity principal = memberService.login(loginRequestDto);
        System.out.println("memberService register 통과");

        if(principal==null) throw new Exception("로그인실패");
       /* if (principal == null) {
            System.out.println("로그인:: 실패했습니다");
            return new ResponseDTO<>(HttpStatus.BAD_REQUEST.value(), "로그인실패");
        }*/

        session.setAttribute("principal", principal); //세션만들기
        ResponseDTO response = new ResponseDTO<>(HttpStatus.OK.value(), "로그인성공");
        System.out.println("로그인성공:: "+response);
        return response;
    }

    /*회원가입  */
    @PostMapping("/auth/signUp")
    public ResponseDTO<String> signup(@Valid @RequestBody RegisterRequestDTO registerRequestDTO) {
        System.out.println("MemberApiController post: save 호출" + registerRequestDTO.getUsername());
        System.out.println("MemberApiController post: save 호출" + registerRequestDTO.getPassword());
        System.out.println("MemberApiController post: save 호출" + registerRequestDTO.getEmail());

        memberService.register(registerRequestDTO);
        System.out.println("memberService register 통과");

        ResponseDTO response = new ResponseDTO<String>(HttpStatus.OK.value(), "회원가입성공"); //(HttpStatus status,data)

        System.out.println("memberService 회원가입성공 :: "+response);
        return response;
    }

}


// 웹에 데이터를 RETURN,
// userentity(오브젝트) to data(json) 변환 :: 메세지 컨버터