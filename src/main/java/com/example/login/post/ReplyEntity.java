package com.example.login.post;

import com.example.login.common.BaseTimeEntity;
import com.example.login.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "reply")
public class ReplyEntity extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //replyId

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "postId")  //fk
    private PostEntity postEntity;

    @ManyToOne(cascade = CascadeType.DETACH) // 연관관계 : 읽기옵션 m:1
    @JoinColumn(name = "userId")     //fk  db는 오브젝트 저장 못함
    private UserEntity userEntity;  //객체지향 오브젝트 저장가능

//    @CreationTimestamp
//    private Timestamp createdAt;
//    @UpdateTimestamp
//    private Timestamp modifiedAt;


}
