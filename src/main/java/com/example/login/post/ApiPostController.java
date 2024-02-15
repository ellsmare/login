package com.example.login.post;

import com.example.login.user.ResponseDto;
import com.example.login.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ApiPostController {
    public final PostService postService;
    public final PostRepository postRepository;

/*
    //페이징
     @GetMapping("/list")
    public String listMethod(Model model,
                             @PageableDefault(
                                     page = 0,
                                     size = 10,
                                     sort = "id",
                                     direction = Sort.Direction.DESC)
                                     Pageable pageable,
                                     String searchKeyword )
    {

        Page<BoardEntity> list = null;
        if (searchKeyword == null) {
            // 검색기능이 없다면, 그냥 다 불러오고
            list = boardRepository.findAll(pageable);
        } else {
            // searchKeyword= 값이 있다면, 해당 값과 일치하는 정보를 갖고옴
            list = boardRepository.findByTitleContaining(searchKeyword, pageable);
        }
[출처] 다시 만들어본 게시판|작성자 민우의 코딩일지

    // 게시글 수정
    @PutMapping("/post/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return ResponseEntity.ok(boardService.updateBoard(id, requestDto, request));
    }

    // 게시글 삭제
    @DeleteMapping("/post/{id}")
    public ResponseEntity<MsgResponseDto> deleteBoard(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(boardService.deleteBoard(id, request));
    }

    // 게시글 전체 조회
    @GetMapping("/post")
    public ResponseEntity<List<BoardResponseDto>> getBoardList() {
        return ResponseEntity.ok(boardService.getBoardList());
    }

    // 게시글 선택 조회
    @GetMapping("/post/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }
    */

    // post save

  @PostMapping("/post")
    public ResponseDto<String> save(@RequestBody PostFormDto postFormDTO, UserEntity userEntity) {
        System.out.println("save post ::" + postFormDTO);

        // 포함된 데이터를 게시물 작성하기
        postService.savePost(postFormDTO, userEntity);

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
