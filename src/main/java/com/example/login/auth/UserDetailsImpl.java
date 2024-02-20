package com.example.login.auth;

import com.example.login.user.UserEntity;
import com.example.login.user.UserRole;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;



// 시큐리티의 세션저장소에 UserDetailsImpl가 저장    UserDetailsServiceImpl
public class UserDetailsImpl implements UserDetails {

    private final UserEntity userEntity; //콤포지션

    public UserDetailsImpl(UserEntity userEntity) { // UserDetailsServiceImpl 필요, 안하면 null
        this.userEntity = userEntity;
    }

    public UserEntity getUser() {
        return userEntity;
    }

    @Override
    public String getPassword() {
        return userEntity.getPassword();
    }

    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //권한반환 Collection<? extends GrantedAuthority //익명메소드 상속> 타입

        UserRole role = userEntity.getRole();
        String authority = role.getAuthority();  // "Role_" 스프링 규칙!!  return :: ROLE_USER/ROLE_ADMIN

        SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(authority);
        System.out.println("---------- PrincipalDetailsImpl simpleGrantedAuthority : " + simpleGrantedAuthority);

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(simpleGrantedAuthority);
        System.out.println("---------- PrincipalDetailsImpl collections: " + authorities);

        return authorities;
    }

    // spring master 10.'Spring Security'-Authentication todo
    // Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());



    @Override
    public boolean isAccountNonExpired() {
        //계정만료여부 true 사용 false 만료
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        //계정잠금여부  true 사용
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        //비번만료여부  true 사용
        return true;
    }

    @Override
    public boolean isEnabled() {
        //계정활성화여부  true 사용
        return true;
    }
}
//extends 상속