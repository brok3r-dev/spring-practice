package com.spring.practice.service;

import com.spring.practice.common.exception.ApiErrorCode;
import com.spring.practice.common.exception.ApiException;
import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Student;
import com.spring.practice.repository.SchoolRepository;
import com.spring.practice.repository.StudentRepository;
import com.spring.practice.service.impl.StudentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
    @Mock
    StudentRepository studentRepository;

    @Mock
    SchoolRepository schoolRepository;

    StudentService service;

    @BeforeEach
    void setUp() {
        service = new StudentServiceImpl(studentRepository, schoolRepository);
    }

    @Test
    void canRegisterStudent() {
        //given
        School school = new School();
        StudentRequest request = new StudentRequest();
        Student student = new Student(request, school);

        when(schoolRepository.findById(request.getSchoolId())).thenReturn(Optional.of(school));

        //when
        service.registerStudent(request);

        //then
        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);

        verify(schoolRepository).save(school);
        verify(studentRepository).save(captor.capture());

        assertThat(captor.getValue().getName()).isEqualTo(student.getName());
    }

    @Test
    void canNotRegisterStudent() {
        //given
        StudentRequest request = new StudentRequest();

        when(schoolRepository.findById(request.getSchoolId())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> service.registerStudent(request))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.SCHOOL_NOT_FOUND.getMessage());
    }

    @Test
    void canUpdateStudent() {
        //given
        String name = "brand new music";
        StudentRequest request = new StudentRequest();
        request.setName(name);

        Student student = new Student();
        School school = new School();
        student.setSchool(school);

        when(studentRepository.findById(request.getId())).thenReturn(Optional.of(student));
        when(schoolRepository.findById(student.getSchool().getId())).thenReturn(Optional.of(school));
        when(schoolRepository.findById(request.getSchoolId())).thenReturn(Optional.of(school));

        //when
        service.updateStudent(request);

        //then
        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);

        verify(studentRepository).save(captor.capture());

        assertThat(captor.getValue().getName()).isEqualTo(name);
    }

    @Test
    void canNotUpdateStudentBecauseNoStudent() {
        //given
        StudentRequest request = new StudentRequest();
        Student student = new Student();
        School school = new School();
        student.setSchool(school);

        when(studentRepository.findById(request.getId())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> service.updateStudent(request))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.STUDENT_NOT_FOUND.getMessage());
    }

    @Test
    void canNotUpdateStudentBecauseNoSchool() {
        //given
        StudentRequest request = new StudentRequest();
        Student student = new Student();
        School school = new School();
        student.setSchool(school);

        when(studentRepository.findById(request.getId())).thenReturn(Optional.of(student));
        when(schoolRepository.findById(student.getSchool().getId())).thenReturn(Optional.empty());
        when(schoolRepository.findById(request.getSchoolId())).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> service.updateStudent(request))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.SCHOOL_NOT_FOUND.getMessage());
    }

    @Test
    void canFindAllStudents() {
        //when
        service.findAllStudents();

        //then
        verify(studentRepository).findAll();
    }

    @Test
    void canFindStudents() {
        //given
        String name = "anyone";

        //when
        service.findStudents(name);

        //then
        verify(studentRepository).findByName(name);
    }

    @Test
    void canFindStudent() {
        //given
        Long id = 1L;

        when(studentRepository.findById(id)).thenReturn(Optional.of(new Student()));

        //when
        service.findStudent(id);

        //then
        verify(studentRepository, times(2)).findById(id);
    }

    @Test
    void canNotFindStudent() {
        //given
        Long id = 1L;

        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> service.findStudent(id))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.STUDENT_NOT_FOUND.getMessage());
    }

    @Test
    void canUnregisterStudent() {
        //given
        Long id = 1L;
        Student student = new Student();
        School school = new School();
        student.setSchool(school);

        when(studentRepository.findById(id)).thenReturn(Optional.of(student));
        when(schoolRepository.findById(student.getSchool().getId())).thenReturn(Optional.of(school));

        //when
        service.unregisterStudent(id);

        //then
        verify(studentRepository, times(2)).findById(id);
        verify(studentRepository).delete(student);
    }

    @Test
    void canNotUnregisterStudent() {
        //given
        Long id = 1L;

        when(studentRepository.findById(id)).thenReturn(Optional.empty());

        //then
        assertThatThrownBy(() -> service.unregisterStudent(id))
                .isInstanceOf(ApiException.class)
                .hasMessage(ApiErrorCode.STUDENT_NOT_FOUND.getMessage());
    }
}