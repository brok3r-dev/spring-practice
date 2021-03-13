package com.spring.practice.service.impl;

import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.entity.School;
import com.spring.practice.repository.SchoolRepository;
import com.spring.practice.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class SchoolServiceImpl implements SchoolService {
    @Autowired
    private SchoolRepository schoolRepository;

    @Override
    @Transactional
    public School postSchool(SchoolRequest schoolRequest) {
        School school = schoolRepository.findByName(schoolRequest.getName());
        if (school != null) { throw new ApiException(ApiErrorCode.SCHOOL_ALREADY_EXIST, HttpStatus.BAD_REQUEST); }
        return schoolRepository.save(new School(schoolRequest));
    }

    @Override
    public List<School> getAllSchools() {
        return schoolRepository.findAll();
    }

    @Override
    public School getSchool(String name) {
        School school = schoolRepository.findByName(name);
        if (school == null) { throw new ApiException(ApiErrorCode.SCHOOL_NOT_FOUND, HttpStatus.NOT_FOUND); }

        return school;
    }
}
