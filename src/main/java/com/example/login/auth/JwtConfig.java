package com.example.login.auth;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
/*Ctrl + Alt + O*/

@Configuration
@EnableWebSecurity // Spring Security 지원을 가능하게 함
public class JwtConfig {
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;


    public JwtConfig(JwtUtil jwtUtil, UserDetailsServiceImpl userDetailsService, AuthenticationConfiguration authenticationConfiguration) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.authenticationConfiguration = authenticationConfiguration;
    }

    // 시큐리티 로그인 가로채기 :: 패스워드 해시 정보 필요
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    //로그인 및 JWT 생성
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    //JWT 검증 및 인가
    @Bean
    public JwtAuthorizationFilter jwtAuthorizationFilter() {
        return new JwtAuthorizationFilter(jwtUtil, userDetailsService);
    }

    //Spring Security 람다 DSL
    //HttpSecurity #authorizeHttpRequests인증 규칙
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 기본 설정인 Session 방식은 사용하지 않고 JWT 방식을 사용하기 위한 설정
        http.sessionManagement((sessionManagement) ->
                sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        );
        http.authorizeHttpRequests((authorizeHttpRequests) -> authorizeHttpRequests
                //.anyRequest().permitAll());
                //.requestMatchers("/css/**", "/js/**", "/images/**", "/plugin/**", "/vendor/**", "/api/v1", "/api/v1/auth/signup").permitAll()    // 정적 리소스
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
                .requestMatchers("/api/v1","/api/v1/auth/**").permitAll()                                     // 시작하는 요청 모두 접근
                .requestMatchers("/api/v1/users/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")

               .anyRequest().authenticated()
        );

        http
                .formLogin((formLogin) -> formLogin         //폼로그인 사용설정
                        .loginPage("/api/v1/auth/login-page")      // 로그인 View 제공
                        .loginProcessingUrl("/api/v1/auth/login")  // 로그인 처리 Url(POST /api/v1/auth/login)
                        .successHandler(new JwtCustomSuccessHandler())
                        //.defaultSuccessUrl("/", false) // 로그인 완료 후 이전 페이지 이동
                        //.failureUrl("/auth/error-form")     // 로그인 실패 URL (기본 에러 페이지로 넘어감)
                        .permitAll()
                );

        http
                .logout((logout) -> logout
                        .logoutRequestMatcher(new AntPathRequestMatcher("/api/v1/users/logout"))
                        // 로그아웃 성공 시 리다이렉트할 URL 설정
                        .logoutSuccessUrl("/api/v1")
                        .permitAll()
                );

        http.csrf((csrf) -> csrf.disable());
        http.httpBasic((httpBasic) -> httpBasic.disable());

        // 필터 관리
        http.addFilterBefore(jwtAuthorizationFilter(), JwtAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }



}

