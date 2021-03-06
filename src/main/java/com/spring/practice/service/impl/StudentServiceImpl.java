package com.spring.practice.service.impl;

import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Student;
import com.spring.practice.entity.Teacher;
import com.spring.practice.repository.SchoolRepository;
import com.spring.practice.repository.StudentRepository;
import com.spring.practice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository, SchoolRepository schoolRepository) {
        this.studentRepository = studentRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    @Transactional
    public Student registerStudent(StudentRequest studentRequest) {
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
    @Transactional
    public Student updateStudent(StudentRequest studentRequest) {
        if (studentRepository.findById(studentRequest.getId()).isPresent()) {
            Student student = studentRepository.findById(studentRequest.getId()).get();

            // TODO: unregister from previous school
            if (schoolRepository.findById(student.getSchool().getId()).isPresent()) {
                School school = schoolRepository.findById(student.getSchool().getId()).get();
                school.unregisterStudent(student);
            }

            // TODO: register to new school
            // TODO: change student's school
            if (schoolRepository.findById(studentRequest.getSchoolId()).isPresent()) {
                School school = schoolRepository.findById(studentRequest.getSchoolId()).get();
                student.moveSchool(school);
                school.registerStudent(student);
            } else {
                throw new ApiException(ApiErrorCode.SCHOOL_NOT_FOUND, HttpStatus.NOT_FOUND);
            }

            // TODO: update student profile
            student.updateStudent(studentRequest);
            studentRepository.save(student);
            return student;
        } else {
            throw new ApiException(ApiErrorCode.STUDENT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Student> findAllStudents() {
        PageRequest request = PageRequest.of(0, 2, Sort.sort(Student.class).by(Student::getId).ascending());

        Page<Student> result = studentRepository.findAll(request);

        return result.getContent();
    }

    @Override
    public List<Student> findStudents(String name) {
        return studentRepository.findByName(name);
    }

    @Override
    public Student findStudent(Long id) {
        if (studentRepository.findById(id).isPresent()) {
            return studentRepository.findById(id).get();
        } else {
            throw new ApiException(ApiErrorCode.STUDENT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public Student unregisterStudent(Long id) {
        if (studentRepository.findById(id).isPresent()) {
            Student student = studentRepository.findById(id).get();

            // TODO: unregister from school
            if (schoolRepository.findById(student.getSchool().getId()).isPresent()) {
                School school = schoolRepository.findById(student.getSchool().getId()).get();
                school.unregisterStudent(student);
            }

            studentRepository.delete(student);
            return student;
        } else {
            throw new ApiException(ApiErrorCode.STUDENT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
