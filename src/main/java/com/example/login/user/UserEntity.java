package com.example.login.user;

import com.example.login.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "user")
public class UserEntity {
    @Id //식별자 pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;

    @Column(nullable = false, unique = true)
    private String username; //아이디

    @Column(nullable = false)
    private String password; //패스워드

    @Column(nullable = false, unique = true)
    private String email; //이메일

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //Enum의 이름을 DB에 그대로 저장
    private UserRole role; //ROLE_USER, ROLE_ADMIN
    // private Role role; //ROLE_USER, ROLE_ADMIN

    private String nickname;   //별명, 기본값 loginId
    private String infoText;   //코멘트창

    @CreationTimestamp
    @Column(updatable = false)
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp modifiedAt;

    public UserEntity(String nickname, String username, String password, String email, UserRole role) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }


}
