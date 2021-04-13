package com.spring.practice.data.response;

import com.spring.practice.entity.Teacher;
import lombok.Getter;

@Getter
public class TeacherResponse {
    private final Long id;
    private final String name;
    private final String address;
    private final String phoneNumber;
    private final Long schoolId;
    private final String fcmToken;

    public TeacherResponse(Teacher teacher) {
        this.id = teacher.getId();
        this.name = teacher.getName();
        this.address = teacher.getAddress();
        this.phoneNumber = teacher.getPhoneNumber();
        this.schoolId = teacher.getId();
        this.fcmToken = teacher.getFcmToken();
    }
}
