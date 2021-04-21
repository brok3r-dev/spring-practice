package com.spring.practice.repository;

import com.spring.practice.entity.Teacher;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TeacherRepositoryTest {
    @Mock
    TeacherRepository teacherRepository;

    Long id = 1L;
    String name = "A";
    Teacher teacher = new Teacher();

    @AfterEach
    void tearDown() {
        teacherRepository.deleteAll();
    }

    @Test
    void canSaveTeacher() {
        //given
        Teacher teacher = new Teacher();

        //when
        teacherRepository.save(teacher);

        //then
        verify(teacherRepository).save(teacher);
    }

    @Test
    void canFindByNameAndSchoolId() {
        //given
        when(teacherRepository.findByNameAndSchoolId(name, id)).thenReturn(teacher);

        //when
        teacherRepository.findByNameAndSchoolId(name, id);

        //then
        ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);

        verify(teacherRepository).findByNameAndSchoolId(name, id);
        verify(teacherRepository).findByNameAndSchoolId(captor.capture(), eq(id));

        assertThat(captor.getValue()).isEqualTo(name);
    }

    @Test
    void canNotFindByNameAndSchoolId() {
        //when
        Teacher found = teacherRepository.findByNameAndSchoolId("GHOST", id);

        //then
        assertThat(found).isNull();
    }
}