//package com.example.login.post.file;
//
//import com.example.login.user.ResponseDto;
//import jakarta.annotation.Resource;
//import lombok.RequiredArgsConstructor;
//import org.apache.commons.io.FilenameUtils;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.File;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/v1/file")
//@RequiredArgsConstructor
//public class FileController {
//
//    private final FileRepository fileRepository;
//    private final FileServiceImpl fileService;
//
//    @PostMapping("/upload")
//
//    public String upload(MultipartFile file
//    ) throws Exception {
//
//        fileService.save(file);
//
//        return "";
//    }
//
//    @GetMapping("/get/{file}")
//
//    public ResponseDto<String> getFile(@PathVariable String file, Model model){
//
//        Resource resource = fileService.getFile(file);
//        if(resource != null){
//            model.addAttribute(ResponseEntity.ok()
//                            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=")
//
//                    .body(resource));
//            return new ResponseDto<>(HttpStatus.OK.value(),"파일 조회");
//
//
//        }
//
//    }
//
//
//}