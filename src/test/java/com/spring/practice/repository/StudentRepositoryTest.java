package com.spring.practice.repository;

import com.spring.practice.entity.Student;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class StudentRepositoryTest {
    @Autowired
    StudentRepository repository;

    Student studentA = new Student();
    String studentAName = "Student A";

    @BeforeEach
    void setUp() {
        studentA.setName(studentAName);
        repository.save(studentA);
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void canSaveStudent() {
        //given
        Student student = new Student();

        //when
        Student saved = repository.save(student);

        //then
        assertThat(saved).isEqualTo(student);
    }

    @Test
    void canFindByName() {
        //when
        List<Student> found = repository.findByName(studentAName);

        //then
        assertThat(found.get(0)).isEqualTo(studentA);
    }

    @Test
    void canNotFindByName() {
        //when
        List<Student> found = repository.findByName("not a human");

        //then
        assertThat(found).isEmpty();
    }
}