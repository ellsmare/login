package com.example.login.post;

import com.example.login.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
//@Transactional
public class PostService {
    private final PostRepository postRepository;


    // 게시글 삭제
    public void deletePost(Long id) {
        System.out.println("____글삭제 deletePost: ");
        postRepository.deleteById(id);
    }


    // 게시글 수정
    @Transactional
    public void updatePost(Long id, PostEntity post) {
        System.out.println("____글수정 updatePost: " + post);
        System.out.println("____글수정 updatePost: " + id);
        PostEntity postEntity = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("페이지를 찾을 수 없습니다.")
        );
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());

      //  postRepository.update(post);//
    }


    //상세 페이지
    //게시글 선택 조회 getPos -- 조회?
    public PostEntity getPost(long id) {
        System.out.println("___________________________________getPost id : " + id);
        return postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("페이지를 찾을 수 없습니다.")
        );


    }


    //글작성
    @Transactional
    public void savePost(PostFormDto postFormDTO, UserEntity principal) {
        System.out.println("____글쓰기 savePost: " + principal);

        PostEntity postEntity = PostEntity.builder()
                .count(0)
                .title(postFormDTO.getTitle())
                .content(postFormDTO.getContent())
                .userEntity(principal)
                .build();

       postRepository.save(postEntity);
        System.out.println("____글쓰기 savePost  postRepository 통과: " + principal);

    }

    //게시글 목록
    public Page<PostEntity> postList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}


//HttpServletRequest
//헤더(Header)에 담긴 jwt 토큰을 담고있는 객체
//즉, 로그인한 사용자의 정보다.