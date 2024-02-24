package com.example.login.post.file;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class PostFileRequestDto {

    private List<MultipartFile> files;

}
