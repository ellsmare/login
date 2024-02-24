package com.example.login.post;

import com.example.login.auth.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/api/v1")
public class PostController {

    private final PostService postService;
    /** controller  return "templates path"   -form */

    // 게시판 상세 페이지(수정,삭제 버튼) /user권한필요  getPostDetail findById user권한필요
    @GetMapping("/users/posts/{id}")
    public String getPostDetail(@PathVariable Long id, Model model) {
        System.out.println("_____findPostById : "+id);

        model.addAttribute("posts", postService.getPost(id));
        return "user/post/postDetailForm";
    }


    // 게시판 글쓰기 페이지(에디터 폼) user권한필요
    @GetMapping("/users/post-edit")
    public String getPostEdit(@AuthenticationPrincipal UserDetailsImpl userDetails, Model model) {
//todo
        return "user/post/postEditForm";
    }



    // 게시판 목록 페이지 boards-page
    @GetMapping("/auth/posts")
    public String getPosts(Model model, @PageableDefault(size = 7, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {

        model.addAttribute("posts",postService.postList(pageable));  //pageable
        return "user/post/postListForm";
    }

}




