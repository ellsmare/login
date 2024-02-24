package com.example.login.post.file;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
public class FileVo {

    private List<MultipartFile> files;
    private String field1;
    private String field2;
    private String field3;

    public FileVo(List<MultipartFile> files, String field1, String field2, String field3, String field4) {
        this.files = files;
        this.field1 = field1;
        this.field2 = field2;
        this.field3 = field3;
        this.field4 = field4;
    }

    private String field4;


}