package com.example.login.post.file;

import com.example.login.post.PostEntity;
import com.example.login.user.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "postFile")
public class PostFileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  //fileId

    @Column(nullable = false)
    private String fileName; // 파일 원본명

    @Column(nullable = false)
    private String filepath; // 파일이 저장된 경로


    private Long fileSize; // 파일 크기
    private String fileExt; //확장자

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "postId")
    private PostEntity postEntity;  // m:1  이미지:게시글


    public PostFileEntity(Long id, String fileName, String filepath, Long fileSize, String fileExt, PostEntity postEntity) {
        this.id = id;
        this.fileName = fileName;
        this.filepath = filepath;
        this.fileSize = fileSize;
        this.fileExt = fileExt;
        this.postEntity = postEntity;
    }
}




