package com.example.Restaurant_Manager_BE.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;



public interface JwtService {
    String generateToken(UserDetails userDetails);

    String generateRefeshToken(UserDetails userDetails);

    String extractToken(String token);

    boolean isValidateToken(String token, UserDetails userDetails);
}
