### UPDATE (2021년 3월 25일)
>LoggingAdvice.java (added)
>- SchoolController에 대한 log 생성
>![log](https://user-images.githubusercontent.com/61440369/112455330-f59c2e00-8d9c-11eb-8825-c1c852def988.PNG)

```java
@Aspect
@Component
public class LoggingAdvice {...}
```

>Util.java (modified)
>- Singleton 패턴 적용 

```java
private static final Util util = new Util();

public static Util getInstance() {
    return util;
}
```

- - -

#### OBJECTIVES
> ##### Spring Security 개념 공부 및 적용

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
- Last Modified 2021년 3월 25일

- - -