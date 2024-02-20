package com.example.login.auth;

import com.example.login.user.MemberRepository;
import com.example.login.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService  {
    private final MemberRepository memberRepository;

    //시큐리티 로그인 가로채기:: username, password 변수 2개
    //username이 db에 있는지 확인
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity principal = memberRepository.findByUsername(username)
                .orElseThrow(() -> {
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. :" +username);
                });
        
        return new UserDetailsImpl(principal); //조회된 회원 정보(principal) 시큐리티 세션에 user 정보 저장, UserDetailsImpl 타입
    }
}


