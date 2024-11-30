package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.responses.APIResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UploadImgFile {
    ResponseEntity<APIResponse> uploadImg(MultipartFile file) throws IOException;
}
