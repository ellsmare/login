package com.example.login.auth;

import com.example.login.user.LoginRequestDto;
import com.example.login.user.ResponseDto;
import com.example.login.user.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;


@Slf4j(topic = "로그인 및 JWT 생성") //  Authorization 헤더 JWT 토큰을 추출
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    /**
     * 인증 관리자 (Authentication Manager) 사용자를 인증  username+password방식 -- jwt방식 가로채기
     */
    private final JwtUtil jwtUtil;


    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;

        setFilterProcessesUrl("/api/v1/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("***************************attemptAuthentication*********************");
        log.info("로그인 시도 request :: " + request);
        try {
            /*  // form post
            log.info("로그인 시도 getParameter :: " +request.getParameter(getUsernameParameter()));

            LoginRequestDto requestDto = new LoginRequestDto();
            requestDto.setUsername(request.getParameter(getUsernameParameter()));
            requestDto.setPassword(request.getParameter(getPasswordParameter()));

            log.info("______________getParameter   requestDto :: " + requestDto);*/

            //json to java object
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            log.info("로그인 시도  requestDto :: " + requestDto);

            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) {    //IOException
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        log.info("************************** successfulAuthentication *********************");
         log.info("authResult :: " +authResult);

        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRole role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();
        String token = jwtUtil.createToken(username, role);
        log.info(" token :: " + token);


        jwtUtil.addJwtToCookie(token, response); //쿠키에 담기
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().write("Cookie 추가 성공");
        response.getWriter().flush();


       /*
       // spring master 4주차
       //response.addHeader(JwtUtil.AUTHORIZATION_HEADER, token); //헤더 담기 (프론트단 방식지정 필요) 응답에 헤더를 넣은 이유는?>?

       // 토큰 생성 AccessToken (쿠키), refreshToken(헤더)
       //Set-Cookie
       response.setHeader("Set-Cookie",
                "refreshToken=" + refreshToken + "; Path=/; HttpOnly; SameSite=None; Secure; expires=" + date););

        log.info(" addHeader :: " + response.getHeader(AUTHORIZATION_HEADER));
        log.info(" username :: " + authResult);
        */

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed)  throws IOException  {
        log.error("unsuccessfulAuthentication ___ 로그인 실패");
        response.setStatus(401);
    }
}


