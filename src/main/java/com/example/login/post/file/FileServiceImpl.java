package com.example.login.post.file;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileRepository {


    @Override
    public void save(MultipartFile file) {

        //String originalFilename = file.getOriginalFilename();

        //파일명
        UUID uuid = UUID.randomUUID();  // 랜덤으로 식별자를 생성
        String newFileName = uuid + "_" + file.getOriginalFilename(); // fileName=uuid+

        File path = new File("C:/file/" + newFileName);

        try {
            file.transferTo(path);
        } catch (IOException e) {
            //throw new RuntimeException(e);
            System.out.println(e.getMessage());

        }


        /*     try {
            UserEntity userEntity = memberRepository.findById(id).orElseThrow(
                    () -> new IllegalArgumentException("등록된 사용자가 없습니다.")
            );
            for (MultipartFile file : files) {

                String originalFilename = file.getOriginalFilename();
                //파일명
                UUID uuid = UUID.randomUUID();  // 랜덤으로 식별자를 생성
                String newFileName = uuid + "_" + file.getOriginalFilename(); // fileName=uuid+
                File path = new File("C:/file/" + newFileName);
                file.transferTo(path);

                fileRepository.save(form);
                // todo
            }
        }catch (Exception e){
            System.out.println("파일 저장 실패");
        }*/

        //return list;

    }
}
