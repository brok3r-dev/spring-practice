package com.spring.practice.service;

import com.google.firebase.FirebaseApp;
import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Student;
import com.spring.practice.repository.SchoolRepository;
import com.spring.practice.service.impl.SchoolServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SchoolServiceTest {
    @Mock
    private static SchoolRepository schoolRepository;

    @Mock
    private FirebaseApp firebaseApp;

    private SchoolService schoolService;

    private static final String name = "Test";
    private static School school;

    @BeforeEach
    void setUp() {
        school = new School(name, "", "");
        schoolService = new SchoolServiceImpl(schoolRepository, firebaseApp);
    }

    @Test
    void canRegisterSchool() {
        //given
        SchoolRequest school = new SchoolRequest(name, "", "");

        //when
        schoolService.registerSchool(school);

        //then
        ArgumentCaptor<School> captor = ArgumentCaptor.forClass(School.class);
        verify(schoolRepository).save(captor.capture());

        assertThat(captor.getValue().getName()).isEqualTo(school.getName());
    }

    @Test
    void canUpdateSchool() {
        //given
        String address = "ohio";
        String phoneNumber = "+1america";
        SchoolRequest request = new SchoolRequest(name, address, phoneNumber);

        when(schoolRepository.findByName(name)).thenReturn(school);

        //when
        schoolService.updateSchool(request);

        //then
        ArgumentCaptor<School> captor = ArgumentCaptor.forClass(School.class);

        verify(schoolRepository).findByName(name);
        verify(schoolRepository).save(captor.capture());

        assertThat(captor.getValue().getAddress()).isEqualTo(address);
        assertThat(captor.getValue().getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    void canNotUpdateSchool() {
        //given
        SchoolRequest request = new SchoolRequest(name, "texas", "smash");
        when(schoolRepository.findByName(name)).thenReturn(null);

        //then
        assertThatThrownBy(() -> schoolService.updateSchool(request))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.SCHOOL_NOT_FOUND.getMessage());

        verify(schoolRepository, never()).save(any());
    }

    @Test
    void canAlertAllStudents() {
        //given
        School testSchool = new School();
        testSchool.registerStudent(new Student());

        when(schoolRepository.findByName(name)).thenReturn(testSchool);

        //when
        schoolService.alertAllStudents(name);

        //then
        verify(schoolRepository).findByName(name);
    }

    @Test
    void canNotAlertAllStudentsBecauseNoSchool() {
        //given
        when(schoolRepository.findByName(name)).thenReturn(null);

        //then
        assertThatThrownBy(() -> schoolService.alertAllStudents(name))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.SCHOOL_NOT_FOUND.getMessage());
    }

    @Test
    void canNotAlertAllStudentsBecauseNoStudent() {
        //given
        when(schoolRepository.findByName(name)).thenReturn(school);

        //then
        assertThatThrownBy(() -> schoolService.alertAllStudents(name))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.STUDENT_NOT_FOUND.getMessage());
    }

    @Test
    void canFindAllSchools() {
        //when
        schoolService.findAllSchools();

        //then
        verify(schoolRepository).findAll();
    }

    @Test
    void canFindSchool() {
        //given
        when(schoolRepository.findByName(name)).thenReturn(school);

        //when
        schoolService.findSchool(name);

        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(schoolRepository).findByName(name);
        verify(schoolRepository).findByName(captor.capture());

        assertThat(captor.getValue()).isEqualTo(name);
    }

    @Test
    void canNotFindSchool() {
        //given
        when(schoolRepository.findByName(name)).thenReturn(null);

        //then
        assertThatThrownBy(() -> schoolService.findSchool(name))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.SCHOOL_NOT_FOUND.getMessage());
    }

    @Test
    void canGetAllStudents() {
        //given
        school.registerStudent(new Student());
        when(schoolRepository.findByName(name)).thenReturn(school);

        //when
        schoolService.getAllStudents(name);

        //then
        verify(schoolRepository).findByName(name);
    }

    @Test
    void canNotGetAllStudentsBecauseNoSchool() {
        //given
        when(schoolRepository.findByName(name)).thenReturn(null);

        //then
        assertThatThrownBy(() -> schoolService.getAllStudents(name))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.SCHOOL_NOT_FOUND.getMessage());
    }

    @Test
    void canNotGetAllStudentsBecauseNoStudents() {
        //given
        when(schoolRepository.findByName(name)).thenReturn(school);

        //then
        assertThatThrownBy(() -> schoolService.getAllStudents(name))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.STUDENT_NOT_FOUND.getMessage());
    }

    @Test
    void canUnregisterSchool() {
        //given
        when(schoolRepository.findByName(name)).thenReturn(school);

        //when
        schoolService.unregisterSchool(name);

        //then
        verify(schoolRepository).delete(school);
    }

    @Test
    void canNotUnregisterSchoolBecauseNoSchool() {
        //given
        when(schoolRepository.findByName(name)).thenReturn(null);

        //then
        assertThatThrownBy(() -> schoolService.unregisterSchool(name))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.SCHOOL_NOT_FOUND.getMessage());
    }
}