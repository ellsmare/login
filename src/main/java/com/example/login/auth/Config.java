package com.example.login.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
/*Ctrl + Alt + O*/

@Configuration
@EnableWebSecurity // Spring Security 지원을 가능하게 함
@RequiredArgsConstructor
public class Config {
    private final JwtUtil jwtUtil;
    private final UserDetailsServiceImpl userDetailsService;
    private final AuthenticationConfiguration authenticationConfiguration;


    // 시큐리티 로그인 가로채기 :: 패스워드 해시 정보 필요
    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //로그인 및 JWT 생성
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(jwtUtil);
        filter.setAuthenticationManager(authenticationManager(authenticationConfiguration));
        return filter;
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
                .anyRequest().permitAll());
//                .requestMatchers("/css/**", "/js/**", "/images/**", "/plugin/**", "/vendor/**", "/", "/auth/**").permitAll()    // 정적 리소스
//                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll() // resources 접근 허용 설정
//                .requestMatchers("/","/auth/**").permitAll()                                     // 시작하는 요청 모두 접근
//                .requestMatchers("/users/**").hasRole("USER")
//                .requestMatchers("/admin/**", "/users/**").hasRole("ADMIN")
//
//               .anyRequest().authenticated()
//        );

        http
                .formLogin((formLogin) -> formLogin         //폼로그인 사용설정
                        .loginPage("/auth/login-form")      // 로그인 View 제공
                        //.defaultSuccessUrl("/", false) // 로그인 완료 후 이전 페이지 이동
                        //.failureUrl("/auth/error-form")     // 로그인 실패 URL (기본 에러 페이지로 넘어감)
                       // .loginProcessingUrl("/auth/login")  // 로그인 처리 Url(POST /api/user/login)
                        .permitAll()
                );

        http
                .logout((logout) -> logout
                        //.invalidateHttpSession(true)
                        //.deleteCookies("JSESSIONID")
                        .logoutSuccessUrl("/")
                        //.logoutRequestMatcher(new AntPathRequestMatcher("/users/logout"))
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
