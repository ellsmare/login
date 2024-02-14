package com.example.login.post;

import com.example.login.user.UserEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDTO {

    private Long postId;
    private String title;
    private String content;    //썸머노트 라이브러리
    private Long writerMno;    // UserEntity fk
    private String writerName; // UserEntity

    private int count; //조회수
    private UserEntity userEntity;  //객체지향 오브젝트 저장가능
    private Timestamp createdAt;
    private Timestamp modifiedAt;

    public PostResponseDTO(PostEntity postEntity) {
        this.postId = postId;
        this.title = title;
        this.content = content;
        this.writerMno = writerMno;
        this.writerName = writerName;
        this.count = count;
        this.userEntity = userEntity;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
