package com.example.login.post;

import com.example.login.post.file.FileRepository;
import com.example.login.post.file.PostFileEntity;
import com.example.login.post.file.PostFileRequestDto;
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
import java.io.IOException;
import java.nio.file.Files;
import java.util.UUID;

@RequiredArgsConstructor
@Service
//@Transactional
public class PostService {
    private final PostRepository postRepository;
    private final FileRepository fileRepository;


    // 게시글 삭제
    public void deletePost(Long id) {
        System.out.println("____글삭제 deletePost: ");
        postRepository.deleteById(id);
    }

    //상세 페이지 조회
    public PostResponseDto getPost(long id) {
        System.out.println("_____상세페이지 조회 : " + id);
        PostEntity posts = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("페이지를 찾을 수 없습니다.")
        );

        PostResponseDto responseDto = new PostResponseDto(posts);

        //System.out.println("_____상세페이지 조회 : " + posts);
        System.out.println("_____상세페이지 조회 : " + responseDto);
        return responseDto;
    }







    /*


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


    //글작성
    @Transactional
    public void savePost(PostRequestDto postRequestDto, PostFileRequestDto postFileRequestDto, UserEntity principal) {
        System.out.println("____글쓰기 : " + principal);

        // 글 저장
        PostEntity postDto = PostEntity.builder()
                .count(0)
                .title(postRequestDto.getTitle())
                .content(postRequestDto.getContent())
                .userEntity(principal)
                .build();

        postRepository.save(postDto);

        //img 저장   location: c:/intellij/post/
        //파일 저장할 경우/아닐경우
        if (postFileRequestDto.getFiles() != null && !postFileRequestDto.getFiles().isEmpty()) {
            //MultipartFile 다중 파일 처리
            for (MultipartFile file : postFileRequestDto.getFiles()) {

                // 파일 확장자 추출
                String fileExt =FilenameUtils.getExtension(file.getOriginalFilename());
                String fileName = file.getOriginalFilename();

                //
                UUID uuid = UUID.randomUUID();
                //중복x
                String imageFileName = uuid + "_" + fileName;

                File destinationFile = new File(uploadFolder + imageFileName);

                try {
                    file.transferTo(destinationFile);
                } catch (IOException e) {
                    System.out.println("____파일저장 : " + e.getMessage());
                    throw new RuntimeException(e);
                }

                PostFileEntity image = PostFileEntity.builder()
                        .fileName(fileName)
                        .filepath("/file/" + imageFileName)
                        .fileExt(fileExt)
                        .fileSize()
                        .postEntity(postDto)
                        .build();

                fileRepository.save(image);
            }


        postDto.getId();     //실패시 롤백처리
        System.out.println("____글쓰기 postRepository 통과 postDto : " + postDto.getUserEntity());
    }
}


    //게시글 목록
    public Page<PostEntity> postList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

}


//HttpServletRequest
//헤더(Header)에 담긴 jwt 토큰을 담고있는 객체
//즉, 로그인한 사용자의 정보다.