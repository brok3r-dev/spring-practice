package com.spring.practice.common.config;

import com.spring.practice.common.filter.JwtAuthenticationFilter;
import com.spring.practice.common.filter.JwtVerificationFilter;
import com.spring.practice.data.JwtDto;
import com.spring.practice.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtDto jwtDto;

    /**
     * 비밀번호 인코더를 가져와서 사용함
     * @param passwordEncoder: BCryptPasswordEncoder
     * 참고: PasswordConfig.java
     */
    @Autowired
    public SecurityConfig(PasswordEncoder passwordEncoder, UserDetailsServiceImpl userDetailsService, JwtDto jwtDto) {
        this.passwordEncoder = passwordEncoder;
        this.userDetailsService = userDetailsService;
        this.jwtDto = jwtDto;
    }

    /**
     * JWT 인증 방식 사용
     * @param http: HttpSecurity
     * @throws Exception: exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                //region JWT
//                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)//JWT 특징인 Stateless에 맞춰 세션 설정
//                .and()
//                .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtDto)) //JWT Authentication 필터 적용
//                .addFilterAfter(new JwtVerificationFilter(jwtDto), JwtAuthenticationFilter.class) //JWT Token Verification 필터 적용
                //endregion
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .anyRequest()
                .authenticated()
        //region Form Based Login
                .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/welcome", true)
                .and()
                .rememberMe()
                    .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(10))
                    .key("secured")
                .and()
                .logout()
                    .logoutUrl("/logout")
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID", "remember-me")
                    .logoutSuccessUrl("/login");
        //endregion
    }

    /**
     * UserDetailsService를 참조하여 DB 또는 메모리에 저장된 회원 정보 조회
     * 조회된 데이터를 사용하여 회원 인증 작업
     * @param auth: AuthenticationManagerBuilder
     * @throws Exception: UserNotFoundException
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    /*
     * 메모리에 저장될 사용자를 추가하는 방법
     * InMemoryUserDetailManager 사용
     * */
//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder.encode("password"))
//                .authorities(ADMIN.getGrantedAuthorities())
//                .build();
//
//        UserDetails user = User.builder()
//                .username("user")
//                .password(passwordEncoder.encode("password"))
//                .authorities(USER.getGrantedAuthorities())
//                .build();
//
//        return new InMemoryUserDetailsManager(admin, user);
//    }
}
