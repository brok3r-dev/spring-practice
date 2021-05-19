## UPDATE (2021년 5월 19일)
1. School Integration Test 작성중

```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/test.sql")
public class SchoolTest {
    // test/java/com/spring/practice/SchoolTest.java
}
```

- - -

### WHAT's NEXT
1. Integration Test
2. Cache

- - -

### STACKS
- 언어: Java 8, HTML
- 프레임워크: Spring Boot 2.4.3
- 데이터베이스: MariaDB
- 운영체제: Windows 10

- - -

### AUTHOR
- 만든이: 강재훈
- 제작일: 2021년 03월 13일
- 수정일: 2021년 05월 19일

- - -
