package com.example.Restaurant_Manager_BE.configurations;

import com.example.Restaurant_Manager_BE.services.impl.UserDetailsImpl;
import com.example.Restaurant_Manager_BE.utils.SecurityUtil;
import com.nimbusds.jose.jwk.source.ImmutableSecret;
import com.nimbusds.jose.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Role;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Arrays;
import java.util.List;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Value("${secretKey}")
    private String secretKey;

    private String prefix = "/api";
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                        request -> request
                                .requestMatchers("/",
                                        "/login",
                                        "/swagger-ui/**",
                                        "/swagger-resources/*",
                                        "/v3/api-docs/**",
                                        "/tables",
                                        String.format("%s/tables", prefix),
                                        String.format("%s/products", prefix),
                                        String.format("%s/categories", prefix)).permitAll()

                                .requestMatchers(HttpMethod.GET,
                                        String.format("%s/products", prefix),
                                         String.format("%s/categories", prefix)
                                        ).permitAll()
//                                    .requestMatchers("api/products/**").hasAuthority("SCOPE_USER")
                                .anyRequest().authenticated()

//                                .anyRequest().permitAll()

                )
                .formLogin(login -> login.disable())
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(jwt -> jwt
                        .jwtAuthenticationConverter(jwtAuthenticationConverter())))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .cors(new Customizer<CorsConfigurer<HttpSecurity>>() {
                    @Override
                    public void customize(CorsConfigurer<HttpSecurity> httpSecurityCorsConfigurer) {
                        CorsConfiguration configuration = new CorsConfiguration();
                        configuration.setAllowedOrigins(List.of("*"));
                        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                        configuration.setAllowedHeaders(Arrays.asList("authorization", "content-type", "x-auth-token"));
                        configuration.setExposedHeaders(List.of("x-auth-token"));
                        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
                        source.registerCorsConfiguration("/**", configuration);
                        httpSecurityCorsConfigurer.configurationSource(source);
                    }
                });

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();
        jwtGrantedAuthoritiesConverter.setAuthorityPrefix("ROLE_");

        JwtAuthenticationConverter jwtAuthenticationConverter = new JwtAuthenticationConverter();
        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(jwtGrantedAuthoritiesConverter);
        return jwtAuthenticationConverter;
    }

    private SecretKey getSecretKey() {
        byte[] keyBytes = Base64.from(secretKey).decode();
        return new SecretKeySpec(keyBytes, 0, keyBytes.length, SecurityUtil.JWT_ALGORITHM.getName());
    }

    @Bean
    public JwtEncoder jwtEncoder() {
        return new NimbusJwtEncoder(new ImmutableSecret<>(getSecretKey()));
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        NimbusJwtDecoder jwtDecoder =  NimbusJwtDecoder
                .withSecretKey(getSecretKey())
                .macAlgorithm(SecurityUtil.JWT_ALGORITHM)
                .build();
        return token -> {
            try {
                return jwtDecoder.decode(token);
            }
            catch (Exception e) {
                throw new RuntimeException(e);
            }
        };
    }

}
