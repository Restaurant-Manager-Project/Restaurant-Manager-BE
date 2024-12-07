package com.example.Restaurant_Manager_BE.utils;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class QRcode {
    private String linkQR = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=";
    @Value("${url.host}")
    private String domain;
    public String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }
        return password.toString();
    }

    public String generateQR(String code) {
        return linkQR + domain + "/selectedTable/" + code;
    }

}
