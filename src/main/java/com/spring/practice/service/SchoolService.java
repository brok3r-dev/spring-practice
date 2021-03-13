package com.spring.practice.service;

import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.entity.School;

import java.util.List;

public interface SchoolService {
    School postSchool(SchoolRequest schoolRequest);
    List<School> getAllSchools();
    School getSchool(String name);
}
