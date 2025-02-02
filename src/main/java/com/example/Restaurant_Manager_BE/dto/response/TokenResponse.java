package com.example.Restaurant_Manager_BE.dto.response;


import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class TokenResponse {
    private String accessToken;
    private String refreshToken;
    private String username;


}
