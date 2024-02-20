package com.example.login.post;

import com.example.login.auth.UserDetailsImpl;
import com.example.login.user.MemberRepository;
import com.example.login.user.ResponseDto;
import com.example.login.user.UserEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "post 게시판 api")
@RequiredArgsConstructor
@RestController
//@RequestMapping("/api")
public class ApiPostJwtController {
    public final PostService postService;
    public final PostRepository postRepository;
    public final MemberRepository memberRepository;

    //(로그인 첫 시도, 쿠키지원x)타임리프 같은 템플릿-jsessionid 를 URL에 자동으로 포함 :: 세션
    // :: server.servlet.session.tracking-modes=cookie   todo 리팩토링 or jwt
    //출처: https://jddng.tistory.com/268 [IT 개발자들의 울타리:티스토리]

    //HttpserveltRequest와 HttpSession 차이??


    // 게시글 삭제
    @DeleteMapping("/users/posts/{id}")
    public ResponseDto<String> deleteBoard(@PathVariable Long id, HttpSession session) {
        UserEntity userentity = (UserEntity) session.getAttribute("principal");
        //검증만 사용
        log.info("_____ 삭제 deleteBoard : "+userentity);
        
        postService.deletePost(id);

        return new ResponseDto<>(HttpStatus.OK.value(), "성공");
    }

    // 게시글 수정
    @PutMapping("/users/posts/{id}")
    public ResponseDto<String> updatePost(@PathVariable Long id, @RequestBody PostFormDto requestDto, HttpSession session) {
        UserEntity userentity = (UserEntity) session.getAttribute("principal");
        log.info("_____ 수정 updatePost : "+userentity);


        //로그인 상태==글쓰기 가능, 접근이 불가능하지만 혹시모르니깐
        if (userentity== null) {
            System.out.println(session.getAttribute("loginOk"));
            throw new IllegalArgumentException("글쓰기는 로그인 회원만 가능합니다.");
        }

        //게시글 번호로 게시글 entity   boardRepository.findByIdAndUserId(id, user.getId()).
        PostEntity post = postService.getPost(id);

        //작성자 == 수정자 동일  :: Nickname   (닉네임은 바뀔 수 있다.  
        if (userentity.getId()== post.getUserEntity().getId()) {
            System.out.println(session.getAttribute("loginOk"));
            throw new IllegalArgumentException("글수정은 작성자만 가능합니다.");
        }

        postService.updatePost(id, post);

        return new ResponseDto<>(HttpStatus.OK.value(), "성공");
    }


//@RequestBody PostFormDto postFormDTO,  @AuthenticationPrincipal UserEntity userDetails

    // post save
    @PostMapping("/users/posts")
    public ResponseDto<String> posts(@RequestBody PostFormDto postFormDTO, @AuthenticationPrincipal UserDetailsImpl userDetails, UserEntity principal) {
        log.info("save post postFormDTO ::" + postFormDTO);
        log.info("save post userDetails ::" + userDetails);
        log.info("save post principal ::" + principal);

        // 인증 정보 확인
        UserEntity user = userDetails.getUser();

        log.info("save post getUser ::" + user);

        // postFormDTO 저장
        postService.savePost(postFormDTO, user);
        log.info("____글쓰기 postService 통과: ");

        return new ResponseDto<>(HttpStatus.OK.value(), "글쓰기 성공");
    }

}
