package com.example.login.post;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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


    // 게시판 상세 페이지(수정,삭제 버튼)
    @GetMapping("/user/postDetail-page")
    public String postDetail() {
        return "user/post/postDetail";
    }


    // 게시판 글쓰기 페이지(에디터 폼) user권한필요
    @GetMapping("/user/postEdit-page")
    public String postEdit() {
        return "user/post/postEdit";
    }


    // 게시판 목록 페이지
    @GetMapping("/auth/post-page")
    public String posts(Model model, @PageableDefault(size = 10, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("post",postService.postList());  //pageable
        return "user/post/postList";
    }

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
//[출처] 다시 만들어본 게시판|작성자 민우의 코딩일지

    */

}
