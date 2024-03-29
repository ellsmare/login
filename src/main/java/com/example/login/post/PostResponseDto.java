package com.example.login.post;

import com.example.login.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;    //썸머노트 라이브러리
    private Long writerMno;    // UserEntity fk
    private String writerName; // UserEntity
    private int count; //조회수


}
/** * 게시글 정보를 리턴할 응답(Response) 클래스 * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답 * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지 */
