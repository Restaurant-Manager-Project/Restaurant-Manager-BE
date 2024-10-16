package com.example.Restaurant_Manager_BE.util;
import java.util.Base64;
import java.security.SecureRandom;


public class QRcode {
    //function generate password randomly
    public static String generatePassword(int length) {
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int index = random.nextInt(chars.length());
            password.append(chars.charAt(index));
        }

        return password.toString();
    }
    //encode string :
    public static String encoding(String str){
       String encodedString = Base64.getEncoder().encodeToString(str.getBytes());
       return encodedString;
    }

    public static String decoding(String str){
        byte[] decodedBytes = Base64.getDecoder().decode(str);
        String decodedString = new String(decodedBytes);
       return decodedString;
    }

}
