package com.spring.practice.common.config;

import com.google.common.net.HttpHeaders;
import com.spring.practice.data.JwtDto;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.SecretKey;

@Configuration
public class JwtConfig {
    @Value("${application.jwt.secret-key}")
    private String secretKey;

    @Value("${application.jwt.token-prefix}")
    private String tokenPrefix;

    @Value("${application.jwt.token-expiration-day}")
    private Long tokenExpirationDays;

    @Bean
    public JwtDto getJwtDto() {
        return new JwtDto(
                Keys.hmacShaKeyFor(secretKey.getBytes()),
                tokenPrefix,
                tokenExpirationDays
        );
    }

    public String getAuthorizationHeader() {
        return HttpHeaders.AUTHORIZATION;
    }
}
