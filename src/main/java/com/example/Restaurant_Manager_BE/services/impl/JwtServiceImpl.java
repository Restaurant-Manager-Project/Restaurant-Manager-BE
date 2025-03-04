package com.example.Restaurant_Manager_BE.services.impl;

import com.example.Restaurant_Manager_BE.exceptions.InvalidTokenException;
import com.example.Restaurant_Manager_BE.exceptions.TokenExpiredException;
import com.example.Restaurant_Manager_BE.services.JwtService;
import com.nimbusds.jose.util.Base64;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.accessToken.expiration}")
    private long accessTokenExpire;
    @Value("${jwt.refreshToken.expiration}")
    private long refeshTokenExpire;
    @Value("${jwt.secretKey}")
    private String secretKey;




    // Extract token - get the username from the token
    @Override
    public String extractUsername(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    @Override
    public boolean isValidateToken(String token, UserDetails userDetails) {
        try {
            String username = extractUsername(token);
            return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
        }
        catch (Exception e) {
            return false;
        }
    }


    @Override
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        List<String> permissions = userDetails.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put("permissions", permissions);
        return generateToken(claims, userDetails);
    }


    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        log.info("Permission: {}", userDetails.getAuthorities());
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (accessTokenExpire * 60 * 1000)))
                .signWith(SignatureAlgorithm.HS256, getKey())
                .compact();
    }

    @Override
    public String generateRefeshToken(UserDetails userDetails) {
        log.info("Permission: {}", userDetails.getAuthorities());
        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (refeshTokenExpire * 60 * 60 * 24 * 1000)))
                .signWith(SignatureAlgorithm.HS256, getKey())
                .compact();
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolvers) {
        Claims claims = extractAllClaims(token);
        return claimsResolvers.apply(claims);
    }
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }


    private Key getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
