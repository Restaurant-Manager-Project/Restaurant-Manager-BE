package com.example.Restaurant_Manager_BE.services;

import com.example.Restaurant_Manager_BE.dto.request.RefreshTokenRequest;
import com.example.Restaurant_Manager_BE.dto.request.SignInRequest;
import com.example.Restaurant_Manager_BE.dto.response.TokenResponse;
import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.exceptions.DataNotFoundException;
import com.example.Restaurant_Manager_BE.exceptions.InvalidTokenException;
import com.example.Restaurant_Manager_BE.exceptions.TokenExpiredException;
import com.example.Restaurant_Manager_BE.repositories.AccountRepository;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
@Slf4j
public class AuthenService {
    private final AccountRepository accountRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public TokenResponse authenticate(SignInRequest signInRequest) {
        AccountEntity accountEntity = accountRepository.findByUsername(signInRequest.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(signInRequest.getUsername(), signInRequest.getPassword()));
        }
        catch (Exception e) {
            log.info("Error: {}", e.getMessage());
            throw new BadCredentialsException("Sai tài khoản hoặc mật khẩu");
        }
        String accessToken = "";
        String refreshToken = accountEntity.getRefreshToken();
        if (StringUtils.isNotEmpty(refreshToken) && jwtService.isValidateToken(refreshToken, accountEntity)) {

            accessToken = jwtService.generateToken(accountEntity);
        }
        else {
            accessToken = jwtService.generateToken(accountEntity);
            refreshToken = jwtService.generateRefeshToken(accountEntity);
            accountEntity.setRefreshToken(refreshToken);
            accountRepository.save(accountEntity);
        }
        return TokenResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .username(accountEntity.getUsername())
                .build();
    }

    public TokenResponse refresh(@Valid RefreshTokenRequest refreshTokenRequest) {
        AccountEntity accountEntity = accountRepository.findByRefreshToken(refreshTokenRequest.getRefreshToken())
                .orElseThrow(() -> new DataNotFoundException("Refresh token không tồn tại"));
        if (jwtService.isValidateToken(refreshTokenRequest.getRefreshToken(), accountEntity)) {
            String accessToken = jwtService.generateToken(accountEntity);
            return TokenResponse.builder()
                    .accessToken(accessToken)
                    .username(accountEntity.getUsername())
                    .build();
        }
        try {
            jwtService.extractUsername(refreshTokenRequest.getRefreshToken());
            return null;
        }
        catch (ExpiredJwtException e) {
            throw new TokenExpiredException("Refresh token đã hết hạn");
        }
    }

}
