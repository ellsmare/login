package com.example.login.post;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class PostController {

    private final PostService postService;
    /** controller  return "templates path"   -form */

    // 게시판 상세 페이지(수정,삭제 버튼) user권한필요  getPostDetail findById
    @GetMapping("/users/post-detail-form/{id}")
    public String postDetail(@PathVariable Long id) {
        System.out.println("_____findPostById : "+id);
        postService.getPost(id);
        return "user/post/postDetailForm";
    }


    // 게시판 글쓰기 페이지(에디터 폼) user권한필요
    @GetMapping("/users/post-edit-form")
    public String postEdit() {
        return "user/post/postEditForm";
    }


    // 게시판 목록 페이지 boards-page
    @GetMapping("/auth/post-form")
    public String posts(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("postEntity",postService.postList(pageable));  //pageable
        return "user/post/postListForm";
    }


}




