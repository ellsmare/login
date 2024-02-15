package com.example.login;

import com.example.login.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final MemberRepository memberRepository;
    private final MemberService memberService;









    //findPassword 찾기  -- 비밀번호 post
//
//    @PostMapping("/test/user/findPassword")
//    public ResponseDto<String> findPassword(@RequestBody @Valid PwRequestDto pwRequestDto){
//        if(!memberRepository.existsByEmail(pwRequestDto.getEmail())){
//            System.out.println("existsByEmail 실패했습니다:: email null");
//            throw new IllegalArgumentException("멤버 조회 실패");
//        }
//        Optional<UserEntity> idx = memberRepository.findByIdx(pwRequestDto.getEmail());
//
//        if(idx==null){
//            System.out.println("findByIdx 실패했습니다:: email 불일치");
//            throw new IllegalArgumentException("멤버 조회 실패");
//        }
//
//        if(idx != pwRequestDto.getIdx()){
//
//        }
//        System.out.println("findByIdx: "+idx);
//
//        ResponseDto response = new ResponseDto<>(HttpStatus.OK.value(), "성공");
//        return response;
//    }

//    @PutMapping("/test/user/chagePw")
//    public  Optional<UserEntity> chagePassword(@RequestBody PwRequestDto pwRequestDto){
//        System.out.println(pwRequestDto.getIdx());
//        System.out.println(pwRequestDto.getPassword());
//
//
//
//        if(username==null){
//            System.out.println("findByUsername 실패했습니다:: email 불일치");
//            throw new IllegalArgumentException("멤버 조회 실패");
//        }
//        System.out.println("________findPassword: "+idx);
//        return idx;
//    }
    // 비번 3번



}
