package com.example.Restaurant_Manager_BE.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.responses.APIResponse;
import com.example.Restaurant_Manager_BE.services.UploadImgFile;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadImgFileImpl implements UploadImgFile {
    private final Cloudinary cloudinary;
    private final LocalizationUtils localizationUtils;
    @Override
    public ResponseEntity<APIResponse> uploadImg(MultipartFile file) throws IOException {
        assert file.getOriginalFilename() != null;
        String publicValue = generatePublicValue(file.getOriginalFilename());
        String extension = getFileName(file.getOriginalFilename())[1];
        File uploadFile = convert(file);
        log.info("fileUpload is : {}", uploadFile);
        cloudinary.uploader().upload(uploadFile, ObjectUtils.asMap("public_id", publicValue));
        cleanDisk(uploadFile);
        APIResponse APIResponse = new APIResponse();
        APIResponse.setMessage(localizationUtils.getLocalizedMessage(MessageKeys.UPLOAD_IMG_SUCCESS));
        APIResponse.setResult(cloudinary.url().generate(StringUtils.join(publicValue,".", extension)));
        return ResponseEntity.ok(APIResponse);
    }

    private void cleanDisk(File file){
        try{
            Path filePath = file.toPath();
            Files.delete(filePath);
        }
        catch(Exception e){
            log.error("error");
        }
    }

    private File convert(MultipartFile file) throws IOException {
        assert file.getOriginalFilename() != null;
        File convFile = new File(StringUtils.join(generatePublicValue(file.getOriginalFilename()),getFileName(file.getOriginalFilename())[1]));
        try(InputStream is = file.getInputStream()) {
            Files.copy(is, convFile.toPath());
        }
        return convFile;
    }

    public String generatePublicValue(String originalFilename){
        String fileName = getFileName(originalFilename)[0];
        return StringUtils.join(UUID.randomUUID().toString(), "_",fileName);
    }

    public String[] getFileName(String originalFilename) {
        return originalFilename.split("\\.");
    }
}
