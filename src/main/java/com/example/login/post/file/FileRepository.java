package com.example.login.post.file;

import org.springframework.web.multipart.MultipartFile;

public interface FileRepository {
    void save(MultipartFile file);
}
