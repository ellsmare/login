package com.example.login.post;

import com.example.login.user.MemberRepository;
import com.example.login.user.ResponseDto;
import com.example.login.user.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
public class ApiPostController {
    public final PostService postService;
    public final PostRepository postRepository;
    public final MemberRepository memberRepository;



    // 게시글 삭제
    @DeleteMapping("/user/post/{id}")
    public ResponseDto<String> deleteBoard(@PathVariable Long id, HttpSession session) {
        UserEntity userentity = (UserEntity) session.getAttribute("principal");
        //검증만 사용
        
        System.out.println("_____ 삭제 deleteBoard : "+userentity);
        
        postService.deletePost(id);

        ResponseDto response = new ResponseDto<>(HttpStatus.OK.value(), "성공");
        return response;
    }


    // 게시글 수정
    @PutMapping("/user/post/{id}")
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

        ResponseDto response = new ResponseDto<>(HttpStatus.OK.value(), "성공");
        return response;
    }


    // 게시글 상세보기  getPostDetail
    // 선택 조회   findPostById
    @GetMapping("/user/post/{id}")
    public ResponseDto<String> getPostDetail(@PathVariable Long id) {
        System.out.println("_____findPostById : "+id);

        postService.getPost(id);

        ResponseDto response = new ResponseDto<>(HttpStatus.OK.value(), "성공");
        return response;
    }


    // 게시글 전체 조회  필요없으면 또는 충돌나면 주석
    @GetMapping("/auth/post")
    public ResponseDto<String> postList() {
        if(postRepository.findAll() ==null){
            System.out.println("_____postList 실패");
            throw new IllegalArgumentException("게시글을 찾을 수 없습니다.");
        }
        ResponseDto response = new ResponseDto<>(HttpStatus.OK.value(), "성공");
        return response;

    }


    // post save
    @PostMapping("/user/post")
    public ResponseDto<String> save(@RequestBody PostFormDto postFormDTO, HttpSession session) {
        System.out.println("save post ::" + postFormDTO);
        System.out.println("save post ::" + session);

        UserEntity userentity = (UserEntity) session.getAttribute("principal");
        System.out.println("____글쓰기 savePost: "+userentity);

        //로그인 상태==글쓰기 가능
        if (userentity == null) {
            System.out.println(session.getAttribute("loginOk"));
            throw new IllegalArgumentException("글쓰기는 로그인 회원만 가능합니다.");
        }

        // 게시물 작성하기
        postService.savePost(postFormDTO, userentity);
        System.out.println("____글쓰기 postService 통과: ");
        ResponseDto response = new ResponseDto<>(HttpStatus.OK.value(), "성공");
        return response;
    }




   /*
   // post save jwt
    @PostMapping("/test/post")
    public ResponseDto<String> save(@RequestBody PostFormDto postFormDTO, @AuthenticationPrincipal PrincipalDetail principal)  {
        System.out.println("save post ::" + postFormDTO);


        // 포함된 데이터를 게시물 작성하기
        postService.savePost(postFormDTO, principal.getUser());
        ResponseDto response = new ResponseDto<>(HttpStatus.OK.value(), "성공");
        return response;
    }
*/


}
