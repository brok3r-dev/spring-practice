package com.spring.practice.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;

    /** 비밀번호 인코더
     * 비밀번호 인코더를 가져와서 사용함
     * @param passwordEncoder: 인코더
     * 참고: PasswordConfig.java
     */
    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    /** 권한 제어 및 인증 방법 조정
     * 사용자 마다 사용할 수 있는 API 제어 및 인증 방법 조정
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/index*", "/error*").permitAll() // permits all request for matchers
                //region Role 또는 Permission 마다 사용 가능한 API 제어
                .antMatchers(HttpMethod.POST, "/school/**").hasAuthority(SCHOOL_WRITE.name()) //권한이 없는 경우 403: Forbidden
                .antMatchers(HttpMethod.PUT, "/school/**").hasAuthority(SCHOOL_WRITE.name())
                .antMatchers(HttpMethod.DELETE, "/school/**").hasAuthority(SCHOOL_WRITE.name())
                .antMatchers(HttpMethod.GET, "/school/**").hasAnyRole(ADMIN.name(), USER.name())
                .antMatchers(HttpMethod.POST, "/student/**").hasAuthority(STUDENT_WRITE.name())
                .antMatchers(HttpMethod.PUT, "/student/**").hasAuthority(STUDENT_WRITE.name())
                .antMatchers(HttpMethod.DELETE, "/student/**").hasAuthority(STUDENT_WRITE.name())
                .antMatchers(HttpMethod.GET, "/student/**").hasAnyRole(ADMIN.name(), USER.name())
                //endregion
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic(); // Pop-up authentication window
    }

    /** 서버 사용자 추가
     * 메모리에 저장될 사용자를 추가하는 방법
     * InMemoryUserDetailManager 사용
     * */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("ADMIN_PASSWORD"))
                .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();

        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder.encode("USER_PASSWORD"))
                .roles(USER.name())
                .authorities(USER.getGrantedAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                admin,
                user
        );
    }
}
