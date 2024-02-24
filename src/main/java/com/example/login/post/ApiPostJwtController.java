package com.example.login.post;

import com.example.login.auth.UserDetailsImpl;
import com.example.login.post.file.PostFileRequestDto;
import com.example.login.user.MemberRepository;
import com.example.login.user.ResponseDto;
import com.example.login.user.UserEntity;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "post 게시판 api")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ApiPostJwtController {
    public final PostService postService;
    public final PostRepository postRepository;
    public final MemberRepository memberRepository;



    // 게시글 삭제
    @DeleteMapping("/users/posts/{id}")
    public ResponseDto<String> deleteBoard(@PathVariable Long id, HttpSession session) {
        UserEntity userentity = (UserEntity) session.getAttribute("principal");
        //검증만 사용
        log.info("_____ 삭제 deleteBoard : " + userentity);

        postService.deletePost(id);

        return new ResponseDto<>(HttpStatus.OK.value(), "성공");
    }

/*

    // 게시글 저장 post??? 업데이트??
    @PutMapping("/users/posts/{id}")
    public ResponseDto<String> updatePost(@PathVariable Long id,
                                          @RequestBody PostRequestDto postRequestDto,
                                          @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        log.info("**** updatePost 시작 *****");

        // 로그인 확인
        UserEntity userEntity = userDetails.getUser();

        // 게시물 확인
        PostEntity postEntity = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("페이지를 찾을 수 없습니다.")
        );

        postService.savePost(postEntity, postRequestDto);

        log.info("**** updatePost service 통과 *****");


        return new ResponseDto<>(HttpStatus.OK.value(), "업데이트 성공");
    }


*/

    // post save
    @PostMapping("/users/posts")
    public ResponseDto<String> posts(@RequestBody PostRequestDto postRequestDto,
                                     @ModelAttribute PostFileRequestDto postFileRequestDto,
                                     @AuthenticationPrincipal UserDetailsImpl userDetails) throws Exception {
        log.info("******posts 게시글 저장*********");

        // 로그인 확인
        UserEntity user = userDetails.getUser();

        // postRequestDTO 저장
        postService.savePost(postRequestDto, postFileRequestDto,user);


        // 반환 정보 추가 :: 팔로우, 좋아요, 댓글
        log.info("******posts 게시글 저장*********");

        return new ResponseDto<>(HttpStatus.OK.value(), "글쓰기 성공");
    }







    /* 이미지 수정중 백업 */
    // post save
/*    @PostMapping("/users/posts")
    public ResponseDto<String> posts(@RequestBody PostRequestDto postRequestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        log.info("******posts 게시글 저장*********");

        // 로그인 확인
        UserEntity user = userDetails.getUser();

        // postRequestDTO 저장
        postService.savePost(postRequestDto, user);

        // 반환 정보 추가 :: 팔로우, 좋아요, 댓글
        log.info("******posts 게시글 저장*********");
        return new ResponseDto<>(HttpStatus.OK.value(), "글쓰기 성공");
    }*/

}
