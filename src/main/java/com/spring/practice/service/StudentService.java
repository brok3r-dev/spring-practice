package com.spring.practice.service;

import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.entity.Student;

import java.util.List;

public interface StudentService {
    Student postStudent(StudentRequest studentRequest);
    List<Student> getAllStudents();
    List<Student> getStudents(String name);
}
