package com.example.Restaurant_Manager_BE.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;


public interface JwtService {
    String generateToken(UserDetails userDetails);

    String generateRefeshToken(UserDetails userDetails);

    String extractUsername(String token);

    boolean isValidateToken(String token, UserDetails userDetails);

    Date extractExpiration(String token);
}
