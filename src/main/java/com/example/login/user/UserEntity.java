package com.example.login.user;

import com.example.login.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "userEntity")
public class UserEntity extends BaseTimeEntity{
    @Id //식별자 pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //userId

    @Column(nullable = false, unique = true)
    private String username; //아이디

    @Column(nullable = false)
    private String password; //패스워드

    @Column(nullable = false, unique = true)
    private String email; //이메일

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //Enum의 이름을 DB에 그대로 저장
    private UserRole role; //ROLE_USER, ROLE_ADMIN

    private String nickname;   //별명, 기본값 loginId
    private String infoText;   //코멘트창

//    @OneToMany(mappedBy = "userEntity", fetch = FetchType.EAGER)  //fk x
//    private List<ReplyEntity> replyEntityList;

//    @CreationTimestamp
//    @Column(updatable = false)
//    private Timestamp createdAt;
//    @UpdateTimestamp
//    private Timestamp modifiedAt;

    public UserEntity(String nickname, String username, String password, String email, UserRole role) {
        this.nickname = nickname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }



}
