package com.spring.practice.common.filter;

import com.google.common.base.Strings;
import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import com.spring.practice.data.JwtDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class JwtVerificationFilter extends OncePerRequestFilter {
    private final JwtDto jwtDto;

    public JwtVerificationFilter(JwtDto jwtDto) {
        this.jwtDto = jwtDto;
    }

    /**
     * 헤더값에 있는 JWT를 가지고 Request 처리
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authorization = request.getHeader("Authorization");

        if (Strings.isNullOrEmpty(authorization) || !authorization.startsWith(jwtDto.getTokenPrefix())) {
            filterChain.doFilter(request, response);
            return;
        }

        try {
            String token = authorization.replace(jwtDto.getTokenPrefix(), "");

            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(jwtDto.getSecretKey())
                    .build()
                    .parseClaimsJws(token);

            Claims body = jws.getBody();

            String username = body.getSubject();

            List<Map<String, String>> authorities = (List<Map<String, String>>) body.get("authorities");

            Set<SimpleGrantedAuthority> grantedAuthorities = authorities.stream()
                    .map(it -> new SimpleGrantedAuthority(it.get("role")))
                    .collect(Collectors.toSet());

            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    grantedAuthorities
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
        } catch (JwtException e) {
            throw new IllegalStateException("Not trusted JWT! " + e.getLocalizedMessage());
        }

        filterChain.doFilter(request, response);
    }
}
