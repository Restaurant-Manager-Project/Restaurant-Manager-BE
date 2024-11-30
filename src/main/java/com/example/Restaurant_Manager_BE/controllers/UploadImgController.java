package com.example.Restaurant_Manager_BE.controllers;

import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.UploadImgFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class UploadImgController {
    private final UploadImgFile uploadImgFile;
    @PreAuthorize("hasRole('upload_img')")
    @PostMapping("/api/upload/img")
    public ResponseEntity<APIResponse> uploadImg(@RequestParam("file") MultipartFile file) throws IOException {
        return uploadImgFile.uploadImg(file);
    }
}
