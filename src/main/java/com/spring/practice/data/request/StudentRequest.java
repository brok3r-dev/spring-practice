package com.spring.practice.data.request;

import com.spring.practice.common.enums.Grade;
import lombok.Getter;

@Getter
public class StudentRequest {
    private Long id;
    private String name;
    private String address;
    private String phoneNumber;
    private Grade grade;
    private Long schoolId;
}
