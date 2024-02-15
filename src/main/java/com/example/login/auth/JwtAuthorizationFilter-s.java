//package com.example.login.auth;
//
//import io.jsonwebtoken.Claims;
//import jakarta.servlet.FilterChain;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContext;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.util.StringUtils;
//import org.springframework.web.filter.OncePerRequestFilter;
//
//import java.io.IOException;
//
//@Slf4j(topic = "JWT 검증 및 인가")
//public class JwtAuthorizationFilter extends OncePerRequestFilter {
//
//
//    private final JwtUtil jwtUtil;
//    private final UserDetailsServiceImpl userDetailsService;
//
//    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
//        this.jwtUtil = jwtUtil;
//        this.userDetailsService = userDetailsService;
//    }
//
//    @Override
//    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain filterChain) throws ServletException, IOException {
//    //OncePerRequestFilter클래스에서 재정의되며 들어오는 각 HTTP 요청을 처리
//
//        //JWT 토큰을 추출
//        String tokenValue = jwtUtil.getTokenFromRequest(req);
//
//        if (StringUtils.hasText(tokenValue)) {
//            // JWT 토큰 substring
//            tokenValue = jwtUtil.substringToken(tokenValue);
//            log.info(tokenValue);
//
//            //토큰이 발견, 토큰의 유효성을 검사
//            if (!jwtUtil.validateToken(tokenValue)) {
//                log.error("Token Error");
//                return;
//            }
//
//            // 토큰에서 사용자 정보 가져오기
//            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);
//
//            try {
//                // 호출, 토큰에서 추출된 사용자 이름을 전달, 사용자 인증을 시도
//                setAuthentication(info.getSubject());
//            } catch (Exception e) {
//                log.error(e.getMessage());
//                return;
//            }
//        }
//
//        filterChain.doFilter(req, res);
//    }
//
//    // 인증 처리
//    public void setAuthentication(String username) {
//
//        //SecurityContext타입 빈객체 생성
//        SecurityContext context = SecurityContextHolder.createEmptyContext();
//        //제공된 사용자 이름을 사용하여 객체를 생성
//        Authentication authentication = createAuthentication(username);
//        //context에 할당
//        context.setAuthentication(authentication);
//
//        SecurityContextHolder.setContext(context);
//    }
//
//    // 인증 객체 생성
//    private Authentication createAuthentication(String username) {
//        // username가 DB에 있는지 확인 후 객체 생성
//        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
//        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//    }
//}