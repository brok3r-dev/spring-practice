package com.spring.practice.data;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.crypto.SecretKey;

@NoArgsConstructor
@Getter
public class JwtDto {
    private SecretKey secretKey;
    private String tokenPrefix;
    private Long tokenExpirationDays;

    public JwtDto(SecretKey secretKey, String tokenPrefix, Long tokenExpirationDays) {
        this.secretKey = secretKey;
        this.tokenPrefix = tokenPrefix;
        this.tokenExpirationDays = tokenExpirationDays;
    }
}
