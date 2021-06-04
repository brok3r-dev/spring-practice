package com.spring.practice;

import com.spring.practice.controller.StudentController;
import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.data.response.StudentResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Sql("/test.sql")
public class StudentIntegrationTest {
    @Autowired StudentController controller;

    private StudentRequest request;
    private final String name = "Test Student";
    private final String address = "in Seoul";
    private final String phoneNumber = "02-000-0000";

    @BeforeEach
    void setUp() {
        request = new StudentRequest();

        request.setName(name);
        request.setAddress(address);
        request.setPhoneNumber(phoneNumber);
        request.setSchoolId(2L);
    }

    @Test
    @WithMockUser(authorities = "student:write")
    void canRegisterStudent() {
        //when
        StudentResponse response = controller.registerStudent(request).getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getAddress()).isEqualTo(address);
        assertThat(response.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    @WithMockUser(authorities = "student:write")
    void canUpdateStudent() {
        //when
        request.setId(5L);
        StudentResponse response = controller.registerStudent(request).getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getAddress()).isEqualTo(address);
        assertThat(response.getPhoneNumber()).isEqualTo(phoneNumber);
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canFindAllStudents() {
        //when
        List<StudentResponse> response = controller.findAllStudents().getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.size()).isGreaterThan(1);
        response.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        assertThat(response.get(0).getId()).isEqualTo(1L);
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canFindStudents() {
        //when
        List<StudentResponse> response = controller.findStudents("Student A").getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.size()).isGreaterThan(1);
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canFindStudent() {
        //when
        StudentResponse response = controller.findStudent(1L).getBody();

        //then
        assertThat(response).isNotNull();
    }

    @Test
    @WithMockUser(authorities = "student:write")
    void canUnregisterStudent() {
        //when
        StudentResponse response = controller.unregisterStudent(1L).getBody();

        //then
        assertThat(response).isNotNull();
    }
}
