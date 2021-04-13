package com.spring.practice.service;

import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.entity.Student;

import java.util.List;

public interface StudentService {
    Student registerStudent(StudentRequest studentRequest);
    Student updateStudent(StudentRequest studentRequest);
    List<Student> findAllStudents();
    List<Student> findStudents(String name);
    Student findStudent(Long id);
    Student unregisterStudent(Long id);
}
