package com.spring.practice.service.impl;

import com.google.firebase.FirebaseApp;
import com.google.firebase.messaging.FirebaseMessaging;
import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import com.spring.practice.common.util.Util;
import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Student;
import com.spring.practice.repository.SchoolRepository;
import com.spring.practice.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SchoolServiceImpl implements SchoolService {
    private final SchoolRepository schoolRepository;
    private final FirebaseApp firebaseApp;

    @Autowired
    public SchoolServiceImpl(SchoolRepository schoolRepository, FirebaseApp firebaseApp) {
        this.schoolRepository = schoolRepository;
        this.firebaseApp = firebaseApp;
    }

    @Override
    @Transactional
    public School registerSchool(SchoolRequest schoolRequest) {
        School school = schoolRepository.findByName(schoolRequest.getName());
        if (school != null) { throw new ApiException(ApiErrorCode.SCHOOL_ALREADY_EXIST, HttpStatus.BAD_REQUEST); }
        return schoolRepository.save(new School(schoolRequest));
    }

    @Override
    @Transactional
    public School updateSchool(SchoolRequest schoolRequest) {
        School school = schoolRepository.findByName(schoolRequest.getName());
        if (school != null) {
            school.updateSchool(schoolRequest);
            schoolRepository.save(school);
            return school;
        } else {
            throw new ApiException(ApiErrorCode.SCHOOL_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public List<School> findAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public School findSchool(String name) {
        School school = schoolRepository.findByName(name);
        if (school == null) { throw new ApiException(ApiErrorCode.SCHOOL_NOT_FOUND, HttpStatus.NOT_FOUND); }

        return school;
    }

    @Override
    public List<Student> getAllStudents(String name) {
        School school = schoolRepository.findByName(name);

        if (school != null) {
            return new ArrayList<>(school.getStudents());
        } else {
            throw new ApiException(ApiErrorCode.SCHOOL_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public School unregisterSchool(String name) {
        School school = schoolRepository.findByName(name);
        if (school != null) {
            // TODO: check existing students before deleting school
            if (school.getStudents().size() > 0) {
                throw new ApiException(ApiErrorCode.SCHOOL_CANNOT_BE_DELETED, HttpStatus.BAD_REQUEST);
            }

            schoolRepository.delete(school);
            return school;
        } else {
            throw new ApiException(ApiErrorCode.SCHOOL_NOT_FOUND, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public boolean alertAllStudents(String name) {
        Set<Student> students = schoolRepository.findByName(name).getStudents();

        if (students == null) {
            throw new ApiException(ApiErrorCode.STUDENT_NOT_FOUND, HttpStatus.NOT_FOUND);
        }

        boolean alertStatus = true;

        for (Student student : students) {
            try {
                FirebaseMessaging.getInstance(firebaseApp).send(Util.getInstance().createMessage(student.getFcmToken()));
            } catch (Exception e) {
                alertStatus = false;
            }
        }

        return alertStatus;
    }
}
