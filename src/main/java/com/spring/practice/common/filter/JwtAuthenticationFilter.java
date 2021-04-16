package com.spring.practice.common.filter;

import com.google.common.base.Strings;
import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import com.spring.practice.data.JwtDto;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private final AuthenticationManager authenticationManager;
    private final JwtDto jwtDto;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager, JwtDto jwtDto) {
        this.authenticationManager = authenticationManager;
        this.jwtDto = jwtDto;
    }

    /**
     * 클라이언트에서 넘어온 credential을 확인하는 단계
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username = request.getHeader("Username");
        String password = request.getHeader("Password");

        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            throw new ApiException(ApiErrorCode.INVALID_AUTH_VALUE, HttpStatus.FORBIDDEN);
        }

        Authentication authentication = new UsernamePasswordAuthenticationToken(username, password);
        return authenticationManager.authenticate(authentication);
    }

    /**
     * 서버에서 클라이언트로 JWT를 보내주는 단계
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(authResult.getName())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusDays(jwtDto.getTokenExpirationDays())))
                .signWith(jwtDto.getSecretKey()) //서버에서 관리하는 Secret Key; 주의! 외부에 알려져서는 안된다.
                .compact();

        response.addHeader("Authorization", jwtDto.getTokenPrefix() + token);
    }
}
