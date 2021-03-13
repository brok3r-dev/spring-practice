package com.spring.practice.service.impl;

import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Student;
import com.spring.practice.repository.SchoolRepository;
import com.spring.practice.repository.StudentRepository;
import com.spring.practice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    @Transactional
    public Student postStudent(StudentRequest studentRequest) {
        if (schoolRepository.findById(studentRequest.getSchoolId()).isPresent()) {
            School school = schoolRepository.findById(studentRequest.getSchoolId()).get();
            Student student = new Student(studentRequest, school);
            school.registerStudent(student);
            schoolRepository.save(school);
            return studentRepository.save(student);
        } else {
            throw new ApiException(ApiErrorCode.SCHOOL_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    @Override
    public List<Student> getStudents(String name) {
        return studentRepository.findByName(name);
    }
}
