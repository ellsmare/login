package com.example.login.auth;

import com.example.login.user.UserRole;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {


    // Header KEY 값
    public static final String AUTHORIZATION_HEADER = "Authorization";

    // 사용자 권한 값의 KEY
    public static final String AUTHORIZATION_KEY = "auth";

    // Token 식별자
    public static final String BEARER_PREFIX = "Bearer ";

    // 토큰 만료시간

    private final long TOKEN_TIME = 864000000; // 10일    60 * 60 * 1000L 60분 ....

    @Value("${jwt.secretKey}") // Base64 Encode 한 SecretKey
    private String secretKey;
    private Key key;
    private final SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    // 로그 설정
    public static final Logger logger = LoggerFactory.getLogger("JWT 관련 로그 JwtUtil");

    @PostConstruct
    public void init() {
        byte[] bytes = Base64.getDecoder().decode(secretKey);
        key = Keys.hmacShaKeyFor(bytes);
    }

    // 토큰 생성 AccessToken
    public String createToken(String username, UserRole role) {
        logger.info("__________JwtUtil createToken : " + username);
        logger.info("__________JwtUtil createToken : " + role);
        Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)           // 사용자 식별자값(ID)
                        .claim(AUTHORIZATION_KEY, role) // 사용자 권한
                        .setExpiration(new Date(date.getTime() + TOKEN_TIME)) // 만료 시간
                        .setIssuedAt(date)                  // 발급일
                        .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                        .compact();
    }

/*

    // 토큰 생성 refreshToken
    public String createReToken(String username) {
        logger.error("__________JwtUtil refreshToken : ");

        //Date date = new Date();

        return BEARER_PREFIX +
                Jwts.builder()
                        .setSubject(username)
                        .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24시간
                        .signWith(key,signatureAlgorithm)
                        .compact();
    }


    // 토큰 생성 AccessToken, refreshToken
    public JwtToken createTwoToken(String username, UserRole role) {
        logger.error("__________JwtUtil createTwoToken : " + username);

        //Access Token 생성
        String accessToken = Jwts.builder()
                .setSubject(username)
                .claim(AUTHORIZATION_KEY, role) // 사용자 권한
                .setExpiration(new Date(date.getTime() + TOKEN_TIME)) // 만료 시간
                .setIssuedAt(date)                  // 발급일
                .signWith(key, signatureAlgorithm) // 암호화 알고리즘
                .compact();


        //Refresh Token 생성
        String refreshToken = Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24)) // 24시간
                .signWith(key,signatureAlgorithm)
                .compact();

        return JwtToken.builder()
                .grantType("Bearer")
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }
*/


    // JWT Cookie 에 저장
    public void addJwtToCookie(String token, HttpServletResponse res) {
        try {

            token = URLEncoder.encode(token, "utf-8").replaceAll("\\+", "%20"); // Cookie Value 에는 공백이 불가능해서 encoding 진행

            Cookie cookie = new Cookie(AUTHORIZATION_HEADER, token); // Name-Value
            cookie.setPath("/");

            logger.info("addJwtToCookie_ cookie getName : "+cookie.getName());
            logger.info("addJwtToCookie_ cookie getValue : "+cookie.getValue());
            logger.info("addJwtToCookie_ cookie getHeaderNames : " +res.getHeaderNames());
            logger.info("addJwtToCookie_ cookie getHeader(token) : " +res.getHeader(cookie.getValue()));

            // Response 객체에 Cookie 추가
            res.addCookie(cookie);
            logger.info("addJwtToCookie_ addCookie getHeaderNames : " +res.getHeaderNames());
            logger.info("addJwtToCookie_ addCookie getHeader(token) : " +res.getHeader(cookie.getValue()));
            logger.info("addJwtToCookie_ addCookie getStatus : " +res.getStatus());

            // 토큰 생성 AccessToken, refreshToken
            // Set-Cookie
            // res.setHeader("Set-Cookie",
            //    "refreshToken=" + refreshToken + "; Path=/; HttpOnly; SameSite=None; Secure; expires=" + date););

            // Access 토큰을 헤드에 저장
            // res.addHeader(AUTHORIZATION_HEADER, token);

            // httpOnly 쿠키 :: XSS 안전, 암호화x, csrf 위협, 문자열만 저장
            // js private variable (localStorage) :: CSRF안전, html5 지원 브라우저, xss 위협

        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage());
        }
    }


    // JWT 토큰 substring 접두사제거
    public String substringToken(String tokenValue) {
        if (StringUtils.hasText(tokenValue) && tokenValue.startsWith(BEARER_PREFIX)) {
            return tokenValue.substring(7);
        }

        logger.error("Not Found Token");
        throw new NullPointerException("Not Found Token");
    }

    // 토큰 검증
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (SecurityException | MalformedJwtException | SignatureException e) {
            logger.error("Invalid JWT signature, 유효하지 않는 JWT 서명 입니다.");
        } catch (ExpiredJwtException e) {
            logger.error("Expired JWT token, 만료된 JWT token 입니다.");
        } catch (UnsupportedJwtException e) {
            logger.error("Unsupported JWT token, 지원되지 않는 JWT 토큰 입니다.");
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims is empty, 잘못된 JWT 토큰 입니다.");
        }
        return false;
    }

    // 토큰에서 사용자 가져오기
    public Claims getUserInfoFromToken(String token) {
        return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
    }

    // HttpServletRequest 에서 Cookie Value : JWT 가져오기 getTokenFromRequest()
    public String getTokenFromRequest(HttpServletRequest req) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(AUTHORIZATION_HEADER)) {
                    try {
                        return URLDecoder.decode(cookie.getValue(), "UTF-8"); // Encode 되어 넘어간 Value 다시 Decode
                    } catch (UnsupportedEncodingException e) {
                        return null;
                    }
                }
            }
        }
        return null;
    }
}