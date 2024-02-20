package com.example.login.post;

import com.example.login.auth.UserDetailsImpl;
import com.example.login.user.MemberRepository;
import com.example.login.user.ResponseDto;
import com.example.login.user.UserEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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
        
        System.out.println("_____ 삭제 deleteBoard : "+userentity);
        
        postService.deletePost(id);

        return new ResponseDto<>(HttpStatus.OK.value(), "성공");
    }

    // 게시글 수정
    @PutMapping("/users/posts/{id}")
    public ResponseDto<String> updatePost(@PathVariable Long id, @RequestBody PostFormDto requestDto, HttpSession session) {
        UserEntity userentity = (UserEntity) session.getAttribute("principal");
        System.out.println("_____ 수정 updatePost : "+userentity);

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
    public ResponseDto<String> posts(@RequestBody PostFormDto postFormDTO, @AuthenticationPrincipal UserDetailsImpl principal) {
        System.out.println("save post ::" + postFormDTO);
        System.out.println("save post ::" + principal);

        // 게시물 작성하기
        postService.savePost(postFormDTO, principal);
        System.out.println("____글쓰기 postService 통과: ");

        return new ResponseDto<>(HttpStatus.OK.value(), "성공");
    }


   /*
   // post save jwt
    @PostMapping("/test/board")
    public ResponseDto<String> save(@RequestBody PostFormDto postFormDTO, @AuthenticationPrincipal PrincipalDetail principal)  {
        System.out.println("save post ::" + postFormDTO);


        // 포함된 데이터를 게시물 작성하기
        postService.saveBoard(postFormDTO, principal.getUser());
        ResponseDto response = new ResponseDto<>(HttpStatus.OK.value(), "성공");
        return response;
    }
*/


}
