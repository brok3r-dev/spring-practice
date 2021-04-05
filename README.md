### UPDATE (2021년 4월 5일)
> 권한 설정 변경
> 1. SecurityConfig.java:
> - antMatchers() 제거
> - @EnableGlobalMethodSecurity(prePostEnabled = true) 추가
> 2. SchoolController.java, StudentController.java:
> - @PreAuthorize() 추가
> 3. Basic -> Form Based Auth 수정

```java
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {...}
```

```java
@PreAuthorize("hasAuthority('student:write')")
ResponseEntity<?> registerStudent(...) {...}
```

```java
@Override
protected void configure(HttpSecurity http) throws Exception {
    http...
        .formLogin() //Form Based Auth
        .loginPage("/login").permitAll()  //Custom Login Page
        .defaultSuccessUrl("/", true)
        .and()
        .rememberMe() //Session ID settings
            .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(10))
            .key("secured");
}
```

- - -

#### OBJECTIVES
> ##### Spring Security 개념 공부 및 개발
> ##### Cache 개념 공부 및 개발

- - -

#### DESCRIPTION
- 언어: Java 8
- 프레임워크: Spring
- 데이터베이스: MariaDB
- 운영체제: Windows 10

- - -

#### INFORMATION
- Created By 강재훈(Jay Kang)
- Created Date 2021년 3월 13일
- Last Modified 2021년 4월 5일

- - -
