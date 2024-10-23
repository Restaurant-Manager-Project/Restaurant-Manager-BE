package com.example.Restaurant_Manager_BE.utils;
import java.security.SecureRandom;


public class QRcode {
    private static String linkQR = "https://api.qrserver.com/v1/create-qr-code/?size=150x150&data=";
    private static String domain = "http://localhost:8080/selectedTable/";
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

    public static String generateQR(String code) {
        return linkQR + domain + code;
    }
    //encode string :
//    public static String encoding(String str){
//       String encodedString = Base64.getEncoder().encodeToString(str.getBytes());
//       return encodedString;
//    }
//
//    public static String decoding(String str){
//        byte[] decodedBytes = Base64.getDecoder().decode(str);
//        String decodedString = new String(decodedBytes);
//       return decodedString;
//    }

}
