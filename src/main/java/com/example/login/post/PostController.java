package com.example.login.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    /** controller  return "templates path"   -form */

    // 게시판 상세 페이지(수정,삭제 버튼) user권한필요
    @GetMapping("/users/board-detail-form")
    public String postDetail() {
        return "user/board/boardDetailForm";
    }


    // 게시판 글쓰기 페이지(에디터 폼) user권한필요
    @GetMapping("/users/board-edit-form")
    public String postEdit() {
        return "user/board/boardEditForm";
    }


    // 게시판 목록 페이지 boards-page
    @GetMapping("/auth/board-form")
    public String posts(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("post",postService.postList());  //pageable
        return "user/board/boardListForm";
    }


}
