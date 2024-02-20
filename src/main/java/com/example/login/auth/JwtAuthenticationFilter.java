package com.example.login.auth;

import com.example.login.user.LoginRequestDto;
import com.example.login.user.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

import static com.example.login.auth.JwtUtil.AUTHORIZATION_HEADER;

@Slf4j(topic = "로그인 및 JWT 생성") //  Authorization 헤더 JWT 토큰을 추출
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    /**
     * 인증 관리자 (Authentication Manager) 사용자를 인증,  username+password방식 -- jwt방식 가로채기
     */
    private final JwtUtil jwtUtil;


    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("***************************로그인 시도*********************");
        log.info("로그인 시도 request :: " + request);
        try {
            //log.info("로그인 시도 getParameter :: " +request.getParameter(getUsernameParameter()));

            //json to java object
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            log.info("로그인 시도:: " + requestDto);

            /*
            // form post
            LoginRequestDto requestDto = new LoginRequestDto();
            requestDto.setUsername(request.getParameter(getUsernameParameter()));
            requestDto.setPassword(request.getParameter(getPasswordParameter()));

            log.info("______________getParameter   requestDto :: " + requestDto);*/

            //return this.getAuthenticationManager().authenticate(authRequest);
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );
        } catch (IOException e) { //IOException
            log.error(e.getMessage());
            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        log.info("***************************로그인 성공 및 JWT 생성*********************");
        //log.info("authResult__ :: "+((UserDetailsImpl) authResult.getPrincipal()).getUsername());

        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRole role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();
        String token = jwtUtil.createToken(username, role);
        log.info("successfulAuthentication token :: " + token);

        jwtUtil.addJwtToCookie(token, response); //쿠키에 담기

        //response.addHeader(AUTHORIZATION_HEADER, token); //헤더 담기 spring master 4주차

        // 토큰 생성 AccessToken, refreshToken
        // Set-Cookie
        // res.setHeader("Set-Cookie",
        //    "refreshToken=" + refreshToken + "; Path=/; HttpOnly; SameSite=None; Secure; expires=" + date););

        log.info("successfulAuthentication addHeader v:: " + response.getHeader(AUTHORIZATION_HEADER));
        log.info("successfulAuthentication username :: " + authResult);


    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        log.info("로그인 실패");
        response.setStatus(401);
    }
}


