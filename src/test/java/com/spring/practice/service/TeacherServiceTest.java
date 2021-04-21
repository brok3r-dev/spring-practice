package com.spring.practice.service;

import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import com.spring.practice.data.request.TeacherRequest;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Teacher;
import com.spring.practice.repository.SchoolRepository;
import com.spring.practice.repository.TeacherRepository;
import com.spring.practice.service.impl.TeacherServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherServiceTest {
    @Mock
    TeacherRepository teacherRepository;

    @Mock
    SchoolRepository schoolRepository;

    TeacherService service;

    @BeforeEach
    void setUp() {
        service = new TeacherServiceImpl(teacherRepository, schoolRepository);
    }

    @Test
    void canRegisterTeacher() {
        //given
        String name = "awesome teacher";
        TeacherRequest request = new TeacherRequest();
        request.setName(name);
        School school = new School();

        when(schoolRepository.findById(request.getSchoolId())).thenReturn(Optional.of(school));

        //when
        service.registerTeacher(request);

        //then
        ArgumentCaptor<Teacher> captor = ArgumentCaptor.forClass(Teacher.class);

        verify(teacherRepository).save(captor.capture());

        assertThat(captor.getValue().getName()).isEqualTo(name);
    }

    @Test
    void canNotRegisterTeacher() {
        //given
        TeacherRequest request = new TeacherRequest();

        when(schoolRepository.findById(request.getSchoolId())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> service.registerTeacher(request))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.SCHOOL_NOT_FOUND.getMessage());
    }

    @Test
    void canUpdateTeacher() {
        //given
        String name = "hugh jackman";
        TeacherRequest request = new TeacherRequest();
        request.setName(name);
        Teacher teacher = new Teacher();
        School school = new School();

        when(teacherRepository.findById(request.getId())).thenReturn(Optional.of(teacher));
        when(schoolRepository.findById(request.getSchoolId())).thenReturn(Optional.of(school));

        //when
        service.updateTeacher(request);

        //then
        ArgumentCaptor<Teacher> captor = ArgumentCaptor.forClass(Teacher.class);

        verify(teacherRepository).save(captor.capture());

        assertThat(captor.getValue().getName()).isEqualTo(name);
    }

    @Test
    void canNotUpdateTeacherBecauseNoTeacher() {
        //given
        TeacherRequest request = new TeacherRequest();

        when(teacherRepository.findById(request.getId())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> service.updateTeacher(request))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.TEACHER_NOT_FOUND.getMessage());
    }

    @Test
    void canNotUpdateTeacherBecauseNoSchool() {
        //given
        TeacherRequest request = new TeacherRequest();
        Teacher teacher = new Teacher();

        when(teacherRepository.findById(request.getId())).thenReturn(Optional.of(teacher));
        when(schoolRepository.findById(request.getSchoolId())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> service.updateTeacher(request))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.SCHOOL_NOT_FOUND.getMessage());
    }

    @Test
    void canFindAllTeacher() {
        //when
        service.findAllTeacher();

        //then
        verify(teacherRepository).findAll();
    }

    @Test
    void canFindTeacher() {
        //given
        String name = "jennifer lawrence";
        Long id = 1L;
        Teacher teacher = new Teacher();

        when(teacherRepository.findByNameAndSchoolId(name, id)).thenReturn(teacher);

        //when
        Teacher found = service.findTeacher(name, id);

        //then
        verify(teacherRepository).findByNameAndSchoolId(name, id);
        assertThat(found).isEqualTo(teacher);
    }

    @Test
    void canNotFindTeacher() {
        //given
        String name = "robert downy junior";
        Long id = 1L;

        when(teacherRepository.findByNameAndSchoolId(name, id)).thenReturn(null);

        //then
        assertThatThrownBy(() -> service.findTeacher(name, id))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.TEACHER_NOT_FOUND.getMessage());
    }

    @Test
    void canUnregisterTeacher() {
        //given
        Long id = 1L;
        Teacher teacher = new Teacher();

        when(teacherRepository.findById(id)).thenReturn(Optional.of(teacher));

        //when
        Teacher deleted = service.unregisterTeacher(id);

        //then
        verify(teacherRepository).delete(teacher);
        assertThat(deleted).isEqualTo(teacher);
    }

    @Test
    void canNotUnregisterTeacher() {
        //given
        Long id = 1L;

        when(teacherRepository.findById(id)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> service.unregisterTeacher(id))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.TEACHER_NOT_FOUND.getMessage());
    }
}