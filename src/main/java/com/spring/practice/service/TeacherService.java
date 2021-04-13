package com.spring.practice.service;

import com.spring.practice.data.request.TeacherRequest;
import com.spring.practice.entity.Teacher;

import java.util.List;

public interface TeacherService {
    Teacher registerTeacher(TeacherRequest teacherRequest);
    Teacher updateTeacher(TeacherRequest teacherRequest);
    List<Teacher> findAllTeacher();
    Teacher findTeacher(String name, Long id);
    Teacher unregisterTeacher(Long id);
}
