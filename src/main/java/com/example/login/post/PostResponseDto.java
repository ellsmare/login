package com.example.login.post;

import com.example.login.post.file.PostFileEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostResponseDto {

    private Long id;
    private String title;
    private String content;    //썸머노트 라이브러리??
    private List<String> filepath;
    private Long writerMno;    // UserEntity fk
    private String writerName; // UserEntity
    private int count; //조회수

    public PostResponseDto(PostEntity postEntity) {
        this.id = postEntity.getId();
        this.title = postEntity.getTitle();
        this.content = postEntity.getContent();
        this.writerMno = postEntity.getUserEntity().getId();
        this.writerName = postEntity.getUserEntity().getNickname();
        this.count = postEntity.getCount();
        this.filepath = postEntity.getPostFile()
                .stream().map(PostFileEntity::getFilepath)
                .collect(Collectors.toList());
    }
}
/** * 게시글 정보를 리턴할 응답(Response) 클래스
 *  * Entity 클래스를 생성자 파라미터로 받아 데이터를 Dto로 변환하여 응답
 * * 별도의 전달 객체를 활용해 연관관계를 맺은 엔티티간의 무한참조를 방지 */
