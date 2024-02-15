package com.example.login.post;

import com.example.login.user.ResponseDTO;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
@RequiredArgsConstructor
@RestController
public class ApiPostController {
    public final PostService postService;
    public final PostRepository postRepository;

/*
    // 게시글 수정
    @PutMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> updateBoard(@PathVariable Long id, @RequestBody BoardRequestDto requestDto, HttpServletRequest request) {
        return ResponseEntity.ok(boardService.updateBoard(id, requestDto, request));
    }

    // 게시글 삭제
    @DeleteMapping("/board/{id}")
    public ResponseEntity<MsgResponseDto> deleteBoard(@PathVariable Long id, HttpServletRequest request) {
        return ResponseEntity.ok(boardService.deleteBoard(id, request));
    }

    // 게시글 전체 조회
    @GetMapping("/board")
    public ResponseEntity<List<BoardResponseDto>> getBoardList() {
        return ResponseEntity.ok(boardService.getBoardList());
    }

    // 게시글 선택 조회
    @GetMapping("/board/{id}")
    public ResponseEntity<BoardResponseDto> getBoard(@PathVariable Long id) {
        return ResponseEntity.ok(boardService.getBoard(id));
    }
    */

    // post save
/*
  @PostMapping("/test/post/save")
    public ResponseDTO<String> save(@RequestBody PostFormDTO postFormDTO, HttpServletRequest request) throws IOException {
        System.out.println("save post ::" + postFormDTO);

        // 포함된 데이터를 게시물 작성하기
        postService.savePost(postFormDTO, request);
        ResponseDTO response = new ResponseDTO<>(HttpStatus.OK.value(), "성공");
        return response;
    }
*/

}
