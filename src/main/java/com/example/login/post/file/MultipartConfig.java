//package com.example.login.post.file;
//
//import jakarta.servlet.MultipartConfigElement;
//import jakarta.servlet.http.HttpServlet;
//import org.springframework.boot.web.servlet.MultipartConfigFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.util.unit.DataSize;
//import org.springframework.web.multipart.MultipartResolver;
//import org.springframework.web.multipart.support.StandardServletMultipartResolver;
//
//@Configuration
//public class MultipartConfig extends HttpServlet {
//
//    @Bean
//    public MultipartResolver multipartResolver() {
//        return new StandardServletMultipartResolver();
//    }
//
//    @Bean
//    public MultipartConfigElement multipartConfigElement() {
//        MultipartConfigFactory factory = new MultipartConfigFactory();
//        factory.setLocation("c:\\intellij\\multipart");
//        factory.setMaxRequestSize(DataSize.ofMegabytes(100L));
//        factory.setMaxFileSize(DataSize.ofMegabytes(100L));
//
//        return factory.createMultipartConfig();
//    }
//}