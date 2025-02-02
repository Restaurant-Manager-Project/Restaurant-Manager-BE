package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.request.SignInRequest;
import com.example.Restaurant_Manager_BE.dto.response.TokenResponse;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthenService {
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    public TokenResponse authenticate(SignInRequest signInRequest) {
        AccountEntity accountEntity = accountRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));


        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword() ));

        String accessToken = jwtService.generateToken(accountEntity);
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken("refresh")
                .username(accountEntity.getUsername())
                .build();
    }



}
