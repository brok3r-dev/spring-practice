### UPDATE (2021년 4월 7일)
> DB를 이용한 회원 관리 시스템
> 1. AuthenticationManagerBuilder를 이용한 회원 정보 조회 방법 사용
> 2. UserDetail 테이블 생성 및 유저 정보 등록
> 3. UserDetailsService implementation 추가

```java
class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {...}

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {...}
}
```

```java
public class UserDetail implements UserDetails {...}
```

```java
public class UserDetailsServiceImpl implements UserDetailsService {...}
```

- - -

#### OBJECTIVES
- Spring Security 개념 공부 및 개발 (80% 완료)
- Cache 개념 공부 및 개발

- - -

#### DESCRIPTION
- 언어: Java 8, HTML
- 프레임워크: Spring
- 데이터베이스: MariaDB
- 운영체제: Windows 10

- - -

#### INFORMATION
- 만든이: 강재훈
- 제작일: 2021년 03월 13일
- 수정일: 2021년 04월 07일

- - -
