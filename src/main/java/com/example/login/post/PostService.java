package com.example.login.post;

import com.example.login.user.UserEntity;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FilenameFilter;
import java.nio.file.Files;
import java.util.UUID;

@RequiredArgsConstructor
@Service
//@Transactional
public class PostService {
    private final PostRepository postRepository;


    // 게시글 삭제
    public void deletePost(Long id) {
        System.out.println("____글삭제 deletePost: ");
        postRepository.deleteById(id);
    }

/*

    // 게시글 저장
    @Transactional
    public void savePost(PostEntity postEntity,
                           PostRequestDto postRequestDto,
                           UserEntity principal) {
        System.out.println("____글쓰기 savePost: " + principal);

        // 작성자 확인
        if(postEntity.getUserEntity().getId() != principal.getId()) {
            throw new IllegalArgumentException("글수정은 작성자만 가능합니다.");
        }

        // 글 저장
        PostEntity postDto = PostEntity.builder()
                .count(0)
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .userEntity(principal)
                .build();

        postRepository.save(postDto);
//
//
//        // CollectionUtils.isEmpty(객체)
//        if(postRequestDto.getFiles() == null || postRequestDto.getFiles().isEmpty()){
//            throw new IllegalArgumentException("저장할 수 없는 파일입니다.");
//        }
//




*/
/*


        // 다중 파일 처리
        for(MultipartFile file : postRequestDto.getFiles()){

            //String absolutePath = new File("").getAbsolutePath() + File.separator + File.separator;
            // 파일 확장자 추출
            String filePath =
                    String fileExt;
            = FilenameUtils.getExtension(file.getOriginalFilename());
            // String mimeType= Files.probeContentType(filePath);



            // 저장폴더  절대경로 yml

            file.transferTo(new File(newFileName));

            File saveFile  = new File(uploadFolder + fileName); //

            //entity 타입

            //리스트 추가

            //파일권한

        }*//*



        System.out.println("____글쓰기 savePost  postRepository 통과: " + principal);


        //postEntity.setTitle(post.getTitle());
        //postEntity.setContent(post.getContent());

       //  postRepository.update(post);//
    }
*/


    //상세 페이지
    public PostEntity getPost(long id) {
        System.out.println("_____상세페이지 조회 : " + id);
        PostEntity posts = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("페이지를 찾을 수 없습니다.")
        );


        System.out.println("_____상세페이지 조회 : " + posts);
        return posts;
    }

    //글작성
    @Transactional
    public void savePost(PostRequestDto postRequestDto, UserEntity principal) {
        System.out.println("____글쓰기 : " + principal);

        // 글 저장
        PostEntity postDto = PostEntity.builder()
                .count(0)
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .userEntity(principal)
                .build();

        postRepository.save(postDto);

        System.out.println("____글쓰기 postRepository 통과 postDto : " + postDto.getUserEntity());
    }

    //게시글 목록
    public Page<PostEntity> postList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}


//HttpServletRequest
//헤더(Header)에 담긴 jwt 토큰을 담고있는 객체
//즉, 로그인한 사용자의 정보다.