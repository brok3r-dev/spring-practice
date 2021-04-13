package com.spring.practice.service.impl;

import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import com.spring.practice.data.request.TeacherRequest;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Teacher;
import com.spring.practice.repository.SchoolRepository;
import com.spring.practice.repository.TeacherRepository;
import com.spring.practice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    private final TeacherRepository teacherRepository;
    private final SchoolRepository schoolRepository;

    @Autowired
    public TeacherServiceImpl(TeacherRepository teacherRepository, SchoolRepository schoolRepository) {
        this.teacherRepository = teacherRepository;
        this.schoolRepository = schoolRepository;
    }

    @Override
    public Teacher registerTeacher(TeacherRequest teacherRequest) {
        if (schoolRepository.findById(teacherRequest.getSchoolId()).isPresent()) {
            School school = schoolRepository.findById(teacherRequest.getSchoolId()).get();
            Teacher teacher = new Teacher(teacherRequest, school);
            return teacherRepository.save(teacher);

        } else {
            throw new ApiException(ApiErrorCode.SCHOOL_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Teacher updateTeacher(TeacherRequest teacherRequest) {
        if (teacherRepository.findById(teacherRequest.getId()).isPresent()) {
            if (schoolRepository.findById(teacherRequest.getSchoolId()).isPresent()) {

                School school = schoolRepository.findById(teacherRequest.getSchoolId()).get();
                Teacher teacher = teacherRepository.findById(teacherRequest.getId()).get();

                teacher.updateTeacher(teacherRequest);
                teacher.moveSchool(school);

                return teacherRepository.save(teacher);

            } else {
                throw new ApiException(ApiErrorCode.SCHOOL_NOT_FOUND, HttpStatus.NOT_FOUND);
            }
        } else {
            throw new ApiException(ApiErrorCode.TEACHER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<Teacher> findAllTeacher() {
        return teacherRepository.findAll();
    }

    @Override
    public Teacher findTeacher(String name, Long id) {
        Teacher teacher = teacherRepository.findByNameAndSchoolId(name, id);

        if (teacher != null) {
            return teacher;
        } else {
            throw new ApiException(ApiErrorCode.TEACHER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Teacher unregisterTeacher(Long id) {
        if (teacherRepository.findById(id).isPresent()) {
            Teacher teacher = teacherRepository.findById(id).get();
            teacherRepository.delete(teacher);
            return teacher;
        } else {
            throw new ApiException(ApiErrorCode.TEACHER_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }
}
