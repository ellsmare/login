//package com.example.login.auth;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import org.springframework.data.redis.core.RedisHash;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.stereotype.Indexed;
//
//import java.util.Collection;
//
//@Builder
//@Getter
//@AllArgsConstructor
//@NoArgsConstructor
//@RedisHash(value = "refresh", timeToLive = 604800)
//public class RefreshToken {
//    private String id;
//
//    private String ip;
//
//    private Collection<? extends GrantedAuthority> authorities;
//
//    @Indexed
//    private String refreshToken;
//}
