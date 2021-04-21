package com.spring.practice.data.request;

import com.spring.practice.common.enums.Grade;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentRequest {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Grade grade;
    private Long schoolId;
    private String fcmToken;
}
