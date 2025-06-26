package com.auction.auctionapp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .allowCredentials(true);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        String uploadPath = new File("uploads/images").getAbsolutePath() + "/";
        registry.addResourceHandler("/uploads/images/**")  // 브라우저 요청 경로
                .addResourceLocations("file:///" + uploadPath);  // 실제 저장 위치

        // 프로필 이미지 수정하기
        String profileUploadPath = new File("uploads/profileImages").getAbsolutePath() + "/";
        registry.addResourceHandler("/profileImages/**")
                .addResourceLocations("file:///" + profileUploadPath);
    }
}