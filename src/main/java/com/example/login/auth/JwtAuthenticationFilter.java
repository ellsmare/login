package com.example.login.auth;

import com.example.login.user.LoginRequestDto;
import com.example.login.user.UserRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.io.PrintWriter;


@Slf4j(topic = "로그인 및 JWT 생성") //  Authorization 헤더 JWT 토큰을 추출
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    /**
     * 인증 관리자 (Authentication Manager) 사용자를 인증  username+password방식 -- jwt방식 가로채기
     */
    private final JwtUtil jwtUtil;

    public JwtAuthenticationFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
        setFilterProcessesUrl("/api/v1/auth/login");  // login-page(html)
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        /* 인증 성공 :: 'authentication token(인증 토큰)'을 반환 */
        /* 인증 진행 중 :: null을 반환, 필요시 null을 반환하기 전에 인증 과정을 완료하기 위해 필요한 추가 작업을 수행   ----  */
        /* 인증 실패 ::  AuthenticationException */
        log.info("***************************attemptAuthentication*********************");

        try {
            log.info("로그인 시도 try:: " + request);

            //json to java object
            LoginRequestDto requestDto = new ObjectMapper().readValue(request.getInputStream(), LoginRequestDto.class);
            log.info("로그인 시도  requestDto :: " + requestDto);

            Authentication token = getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                            requestDto.getUsername(),
                            requestDto.getPassword(),
                            null
                    )
            );

            log.info("로그인 시도  token :: " + token);
            // successfulAuthentication   인증진행중 - null //
            return token;

        } catch (IOException e) {    //IOException
            log.error(e.getMessage());
            log.info("로그인 시도  예외처리 :: " + response.getStatus());

            throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException {
        log.info("************************** successfulAuthentication *********************");

        String username = ((UserDetailsImpl) authResult.getPrincipal()).getUsername();
        UserRole role = ((UserDetailsImpl) authResult.getPrincipal()).getUser().getRole();
        String token = jwtUtil.createToken(username, role);  // 토큰 생성
        log.info(" token :: " + token);

        jwtUtil.addJwtToCookie(token, response); //쿠키에 담기
        log.info(" getStatus :: " + response.getStatus());

        log.info("*******쿠키 저장 로그인 중입니다.*********");

        /* 이미 응답이 200 ... 핸들러 적용 ????    */
        /*반환하기 */

        PrintWriter writer = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        writer.print("{\"status\":\"success\",\"message\":\"login ok\"}");
        writer.close();
        writer.flush();  //버퍼쪽 문제가 없을 거라고 한다.
        //클라이언트쪽 쿠키관련은 전부 빼버렸는데 처리해줘야하는게 맞다는데....
        //지금 동작하는데 어떻하지? 제한 뒀을때 안되면 처리 추가해야 하나?

    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        log.error("unsuccessfulAuthentication ___ 로그인 실패");
        response.setStatus(401);
        //로그인 실패 핸들러
    }
}
