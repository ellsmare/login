package com.example.login.post;

import com.example.login.BaseTimeEntity;
import com.example.login.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class ReplyEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int replyId;

    @Column(nullable = false, length = 200)
    private String content;

    @ManyToOne(cascade = CascadeType.DETACH)
    @JoinColumn(name = "post_id")  //fk
    private PostEntity postEntity;

    @ManyToOne(cascade = CascadeType.DETACH) // 연관관계 : 읽기옵션 m:1
    @JoinColumn(name = "user_id")     //fk  db는 오브젝트 저장 못함
    private UserEntity userEntity;  //객체지향 오브젝트 저장가능

    @CreationTimestamp
    private Timestamp createdAt;
    @UpdateTimestamp
    private Timestamp modifiedAt;


}
