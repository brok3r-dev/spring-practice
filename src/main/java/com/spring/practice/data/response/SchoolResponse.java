package com.spring.practice.data.response;

import com.spring.practice.entity.School;
import com.spring.practice.entity.Student;
import lombok.Getter;

import java.util.Set;

@Getter
public class SchoolResponse {
    private final Long id;
    private final String name;
    private final String address;
    private final String phoneNumber;
    private final Set<Student> students;

    public SchoolResponse(School s) {
        this.id = s.getId();
        this.name = s.getName();
        this.address = s.getAddress();
        this.phoneNumber = s.getPhoneNumber();
        this.students = s.getStudents();
    }
}
