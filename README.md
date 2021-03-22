# OBJECTIVES
>- Spring Security 개념 공부 및 적용
>- Spring Filter, Interceptor, AOP 개념 공부 및 적용
- - -
### UPDATE (2021년 3월 22일)
- Firebase Cloud Messaging 기능 추가
>FCMConfig.java
```java
@Configuration
public FirebaseApp getFirebaseApp() throws Exception {...}
```
> SchoolController.java
```java
@PostMapping("/alert")
ResponseEntity<?> alertAllStudents(...) {...}
```
- - -
#### DESCRIPTION
- 언어: Java 8
- 프레임워크: Spring
- DB: MariaDB
- - -
#### INFORMATION
- created by 강재훈(Jay Kang)
- created date 2021-03-13
- last modified 2021-03-22