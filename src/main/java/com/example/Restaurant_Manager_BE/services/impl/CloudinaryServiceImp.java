package com.example.Restaurant_Manager_BE.services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.example.Restaurant_Manager_BE.constants.MessageKeys;
import com.example.Restaurant_Manager_BE.exceptions.InvalidInputException;
import com.example.Restaurant_Manager_BE.services.CloudinaryService;
import com.example.Restaurant_Manager_BE.utils.LocalizationUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
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
public class CloudinaryServiceImp implements CloudinaryService {
    private final Cloudinary cloudinary;
    private final LocalizationUtils localizationUtils;
    @Override
    public String uploadImg(MultipartFile img) {
        if (img == null) {
            return "";
        }

        String fileName = img.getOriginalFilename();
        if (!fileName.matches(".*\\.(png|jpg|jpeg|gif|bmp)$")) {
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.UPLOAD_IMG_INVALID));
        }

        assert img.getOriginalFilename() != null;
        String publicValue = generatePublicValue(img.getOriginalFilename());
        String extension = getFileName(img.getOriginalFilename())[1];
        try {
            File uploadFile = convert(img);
            log.info("fileUpload is : {}", uploadFile);
            cloudinary.uploader().upload(uploadFile, ObjectUtils.asMap("public_id", publicValue));
            cleanDisk(uploadFile);
            return cloudinary.url().generate(StringUtils.join(publicValue,".", extension));
        }
        catch (IOException e){
            throw new InvalidInputException(localizationUtils.getLocalizedMessage(MessageKeys.UPLOAD_IMG_INVALID));
        }

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
