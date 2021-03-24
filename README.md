# OBJECTIVES
>##### Spring Security 개념 공부 및 적용
- - -
### UPDATE (2021년 3월 24일)
>FilterConfig.java (added)
>- SchoolFilter, StudentFilter를 register하는 역할

```java
@Configuration
public class FilterConfig {...}
```

>CommonFilter.java (added)
>- CommonFilter는 URL 상관없이 항상 거쳐가는 Filter

```java
@Component
@Order(1)
public class CommonFilter implements Filter {...}
```

>SchoolFilter.java, StudentFilter.java (added)

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
- Last Modified 2021년 3월 24일