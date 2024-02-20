package com.example.login.post;

import com.example.login.BaseTimeEntity;
import com.example.login.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "postEntity")
public class PostEntity extends BaseTimeEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id; //postId

    @Column(nullable = false, length = 100)
    private String title;

    @Lob  //대용량
    private String content;  //썸머노트 라이브러리

    private int count; //조회수

    private int hit;   //좋아요

    @ManyToOne(cascade = CascadeType.DETACH) // 연관관계 : 읽기옵션 m:1
    @JoinColumn(name = "userId")  //fk           db는 오브젝트 저장 못함
    private UserEntity userEntity;           //객체지향 오브젝트 저장가능

    @OneToMany(mappedBy = "postEntity", fetch = FetchType.EAGER)  //fk x
    private List<ReplyEntity> reply;

//    @CreationTimestamp
//    @Column(updatable = false)
//    private Timestamp createdAt;
//    @UpdateTimestamp
//    private Timestamp modifiedAt;


    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }


}



//게시글 UI에서 댓글을 바로 보여주기 위해 FetchType을 EAGER로 설정