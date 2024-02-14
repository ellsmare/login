package com.example.login.post;

import com.example.login.user.MemberRepository;
import com.example.login.user.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
//@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

/*

	// 게시글 삭제
    public MsgResponseDto deleteBoard(Long id, HttpServletRequest request) {
        User user = getUserFromToken(request);			// getUserFromToken 메서드를 호출

        Board board = boardRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자의 게시글을 찾을 수 없습니다.")
        );

        boardRepository.delete(board);

        return new MsgResponseDto("게시글을 삭제했습니다.", HttpStatus.OK.value());
    }



	// 게시글 수정
    @Transactional
    public BoardResponseDto updateBoard(Long id, BoardRequestDto requestDto, HttpServletRequest request) {
        User user = getUserFromToken(request);			// getUserFromToken 메서드를 호출

        Board board = boardRepository.findByIdAndUserId(id, user.getId()).orElseThrow(
                () -> new IllegalArgumentException("해당 사용자의 게시글을 찾을 수 없습니다.")
        );

        board.update(requestDto);

        return new BoardResponseDto(board);
    }



    //글작성
    public void savePost(PostFormDTO postFormDTO, HttpServletRequest request) {
        //UserEntity userEntity = getUserFromToken(request);

        PostEntity postEntity = PostEntity(postFormDTO, userEntity);
        //저장
        PostEntity savePost= memberRepository.save(postEntity);
        return savePost;
    }
*/


    //상세 페이지


}
//HttpServletRequest
//헤더(Header)에 담긴 jwt 토큰을 담고있는 객체
//즉, 로그인한 사용자의 정보다.