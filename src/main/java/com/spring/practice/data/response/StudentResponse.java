package com.spring.practice.data.response;

import com.spring.practice.common.enums.Grade;
import com.spring.practice.entity.Student;
import lombok.Getter;

@Getter
public class StudentResponse {
    private final Long id;
    private final String name;
    private final String address;
    private final String phoneNumber;
    private final Grade grade;
    private final SchoolResponse school;

    public StudentResponse(Student student) {
        this.id = student.getId();
        this.name = student.getName();
        this.address = student.getAddress();
        this.phoneNumber = student.getPhoneNumber();
        this.grade = student.getGrade();
        this.school = new SchoolResponse(student.getSchool());
    }
}
