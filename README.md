### UPDATE (2021년 4월 16일)
> Json Web Token (JWT) 기능 적용
> - Form Based 인증 기능 변경
> - JWT 관련 필터 추가

```java
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1. Form Based에서 JWT 변경
    }
}
```

```java
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        // 1. HTTP 요청에서 유저 정보 값을 가져옴
        // 2. 데이터베이스에 저장된 유저 정보 조회 및 인증 
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // 1. 인증 성공 시, JWT 생성 및 응답 헤더 값에 등록
    }
}
```

```java
public class JwtVerificationFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 1. HTTP 요청 헤더에 있는 JWT 값을 조회
        // 2. JWT Decoding
        // 3. 회원 정보 추출 및 인증 처리
    }
}
```

- - -

#### WHAT's NEXT
1. JUnit Test 및 TDD 공부 및 적용
2. Cache 공부 및 적용

- - -

#### STACKS
- 언어: Java 8, Spring Boot, HTML
- 프레임워크: Spring
- 데이터베이스: MariaDB
- 운영체제: Windows 10

- - -

#### AUTHOR
- 만든이: 강재훈
- 제작일: 2021년 03월 13일
- 수정일: 2021년 04월 16일

- - -
