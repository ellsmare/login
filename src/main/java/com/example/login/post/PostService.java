package com.example.login.post;

import com.example.login.user.MemberRepository;
import com.example.login.user.UserEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Service
//@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;


/*


    }*/


    // 게시글 삭제
    public void deletePost(Long id) {
        System.out.println("____글삭제 deletePost: ");

        postRepository.deleteById(id);
    }


    // 게시글 수정
    @Transactional
    public void updatePost(Long id, PostEntity post) {
        System.out.println("____글수정 updatePost: " + post);
        PostEntity postEntity = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("/____글수정/////.")
        );
        postEntity.setTitle(post.getTitle());
        postEntity.setContent(post.getContent());

      //  postRepository.update(post);//
    }


    //상세 페이지
    //게시글 선택 조회 getPos -- 조회?
    public PostEntity getPost(long id) {
        PostEntity post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("//getPost///.")
        );
        return post;
    }


    //글작성  session
    @Transactional
    public PostEntity savePost(PostFormDto postFormDTO, UserEntity userentity) {
        //UserEntity userEntity = getUserFromToken(request);
        System.out.println("____글쓰기 savePost: " + userentity);

        PostEntity postEntity = PostEntity.builder()
                .count(0)
                .title(postFormDTO.getTitle())
                .content(postFormDTO.getContent())
                .userEntity(userentity)
                .build();

        PostEntity savePost = postRepository.save(postEntity);
        if (savePost == null) {
            System.out.println("session 실패:: savePost null");
            throw new IllegalArgumentException("글쓰기에 실패했습니다.");
        }
        return savePost;
    }

    //게시글목록
    public List<PostEntity> postList() {
        return postRepository.findAll();
    }

}


//HttpServletRequest
//헤더(Header)에 담긴 jwt 토큰을 담고있는 객체
//즉, 로그인한 사용자의 정보다.