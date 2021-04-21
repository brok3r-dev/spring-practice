## UPDATE (2021년 4월 21일)
1. 유닛 테스트 작성
- JUnit 5, AssertJ, Mockito 사용
- Repository, DAO 유닛 테스트 작성
- 관련 클래스 폴더: src/test/java/.../repository, .../service

```java
@DataJpaTest
class UserDetailRepositoryTest {
    // 유저 관련 repository 유닛 테스트
}
```

```java
@ExtendWith(MockitoExtension.class)
class UserDetailsServiceTest {
    // 유저 관련 DAO 유닛 테스트
}
```
- - -

### WHAT's NEXT
1. Controller 유닛 테스트 작성
2. Cache 공부 및 적용

- - -

### STACKS
- 언어: Java 8, Spring Boot, HTML
- 프레임워크: Spring
- 데이터베이스: MariaDB
- 운영체제: Windows 10

- - -

### AUTHOR
- 만든이: 강재훈
- 제작일: 2021년 03월 13일
- 수정일: 2021년 04월 21일

- - -
