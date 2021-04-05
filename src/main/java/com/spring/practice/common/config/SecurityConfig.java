package com.spring.practice.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.spring.practice.common.enums.UserPermission.*;
import static com.spring.practice.common.enums.UserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    /**
     * 비밀번호 인코더를 가져와서 사용함
     * @param passwordEncoder: BCryptPasswordEncoder
     * 참고: PasswordConfig.java
     */
    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /** 권한 제어 및 인증 방법 조정
     * 사용자 마다 사용할 수 있는 API 제어 및 인증 방법 조정
     * @param http: HttpSecurity
     * @throws Exception: exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/index*", "/error*").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    /**
     * 메모리에 저장될 사용자를 추가하는 방법
     * InMemoryUserDetailManager 사용
     * */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("password"))
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .authorities(USER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
