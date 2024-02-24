package com.example.login.auth;

import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j(topic = "JWT 검증 및 인가")
public class JwtAuthorizationFilter extends OncePerRequestFilter {
    /* 동일한 요청에 대해 필터가 여러 번 적용되는 것을 방지 */

    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;


    public JwtAuthorizationFilter(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, @NonNull FilterChain filterChain) throws ServletException, IOException {
        log.info("***************************doFilterInternal *********************");

        //JWT 토큰을 추출
        //String tokenValue = jwtUtil.getJwtFromHeader(req);    //헤더
        String tokenValue = jwtUtil.getTokenFromRequest(req);  //쿠키
        log.info("[+] cookieCheck: " + tokenValue);

        if (StringUtils.hasText(tokenValue)) {  //null, 공백

            // JWT 토큰 substring
            tokenValue = jwtUtil.substringToken(tokenValue);  //순수 jwtToken
            log.info("substringToken jwtToken: " + tokenValue);


            if (!jwtUtil.validateToken(tokenValue)) { // 토큰의 유효성을 검사
                log.error("Token validateToken Error");
                res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "try login");
                return;
            }

            // 토큰에서 사용자 정보 반환
            Claims info = jwtUtil.getUserInfoFromToken(tokenValue);          

            try {

                // 토큰에서 추출된 사용자 이름을 전달, 사용자 인증을 시도
                setAuthentication(info.getSubject());

                log.info("*******  end  ********** "+ res.getStatus());

                // 인증 사용자 정보 조회
                UserDetails principal = getAuthentication(info.getSubject());
                log.info("*******  end point ********** "+ principal);


            } catch (Exception e) {
                log.error("null" + e.getMessage());
                return;

            }
        }
        //다음 필터
        filterChain.doFilter(req, res);
    }

    // 사용자 인증 처리, SecurityContext 저장
    public void setAuthentication(String username) {
        log.info("***************************setAuthentication*********************");

        //SecurityContext타입 빈객체 생성
        SecurityContext context = SecurityContextHolder.createEmptyContext();

        //제공된 사용자 이름을 사용하여 객체를 생성
        Authentication authentication = createAuthentication(username);
        log.info("createAuthentication  authentication: " + authentication);
        
        context.setAuthentication(authentication);  //context에 할당        
        SecurityContextHolder.setContext(context);  //보안컨텍스트 홀더에 현재 인증 토큰으로 설정
    }

    // 인증 객체 생성
    private Authentication createAuthentication(String username) {
        log.info("***************************createAuthentication*********************");

        // username가 DB에 있는지 확인 후 객체 생성
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        log.info(" userDetails :: " + userDetails);    
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

    }

    // 인증 정보 조회
    public UserDetails getAuthentication(String username) {
            log.info("***************************getAuthentication*********************");

        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        log.info("userDetails"+ userDetails);
        return userDetails;
    }

}


/*
	public UsernamePasswordAuthenticationToken(Object principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		this.principal = principal;
		this.credentials = credentials;
		super.setAuthenticated(true); // must use super, as we override
	}





            */
