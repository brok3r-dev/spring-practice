package com.spring.practice.service;

import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Student;

import java.util.List;

public interface SchoolService {
    School registerSchool(SchoolRequest schoolRequest);
    School updateSchool(SchoolRequest schoolRequest);
    List<School> findAllSchools();
    School findSchool(String name);
    List<Student> getAllStudents(String name);
    School unregisterSchool(String name);
    boolean alertAllStudents(String name);
}
