package com.example.Restaurant_Manager_BE.utils;

import com.example.Restaurant_Manager_BE.entities.AccountEntity;
import com.example.Restaurant_Manager_BE.repositories.AccountRepository;
import com.example.Restaurant_Manager_BE.repositories.RoleRepository;
import com.example.Restaurant_Manager_BE.services.AccountService;
import com.example.Restaurant_Manager_BE.services.RoleService;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.util.Base64;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityUtil {

//    @Value("${secretKey}")
//    private String secretKey;
    @Value("${expiration}")
    private long expiration;
    public static final MacAlgorithm JWT_ALGORITHM = MacAlgorithm.HS256;
    private final JwtEncoder jwtEncoder;
    private final RoleService roleService;
    private final AccountRepository accountRepository;


    public String createToken(Authentication authentication) {
        Instant now = Instant.now();
        AccountEntity accountEntity = accountRepository.findByUsername(authentication.getName()).get();
        List<String> keyPermission = roleService.getPermissionKey(accountEntity.getRole().getId());
        Instant validity = now.plus(expiration, ChronoUnit.SECONDS);
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(authentication.getName())
                .claim("scope", keyPermission)
                .build();
        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }

}
