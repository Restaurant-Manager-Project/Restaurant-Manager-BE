package com.example.Restaurant_Manager_BE.services;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryService {
    String uploadImg(MultipartFile file);

    String findImage(String name);
}
