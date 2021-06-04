package com.spring.practice;

import com.spring.practice.controller.TeacherController;
import com.spring.practice.data.request.TeacherRequest;
import com.spring.practice.data.response.TeacherResponse;
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
public class TeacherIntegrationTest {
    @Autowired TeacherController controller;

    private TeacherRequest request;
    private final String name = "Taeyeon";
    private final String address = "in Seoul";
    private final String phoneNumber = "prohibited";

    @BeforeEach
    void setUp() {
        request = new TeacherRequest();

        request.setName(name);
        request.setAddress(address);
        request.setPhoneNumber(phoneNumber);
        request.setSchoolId(1L);
    }

    @Test
    @WithMockUser(authorities = "teacher:write")
    void canRegisterTeacher() {
        //when
        TeacherResponse response = controller.registerTeacher(request).getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getAddress()).isEqualTo(address);
        assertThat(response.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(response.getSchoolId()).isEqualTo(1L);
    }

    @Test
    @WithMockUser(authorities = "teacher:write")
    void canUpdateTeacher() {
        //when
        request.setId(1L);
        TeacherResponse response = controller.updateTeacher(request).getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(name);
        assertThat(response.getAddress()).isEqualTo(address);
        assertThat(response.getPhoneNumber()).isEqualTo(phoneNumber);
        assertThat(response.getSchoolId()).isEqualTo(1L);
    }

    @Test
    @WithMockUser(authorities = "teacher:read")
    void canFindAllTeachers() {
        //when
        List<TeacherResponse> response = controller.findAllTeachers().getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.size()).isGreaterThan(1);
    }

    @Test
    @WithMockUser(authorities = "teacher:read")
    void canFindTeacher() {
        //when
        TeacherResponse response = controller.findTeacher("Teacher E", 2L).getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Teacher E");
        assertThat(response.getAddress()).isEqualTo("in Busan");
        assertThat(response.getPhoneNumber()).isEqualTo("051-000-0000");
        assertThat(response.getSchoolId()).isEqualTo(2L);
    }

    @Test
    @WithMockUser(authorities = "teacher:write")
    void canUnregisterTeacher() {
        //when
        TeacherResponse response = controller.unregisterTeacher(1L).getBody();

        //then
        assertThat(response).isNotNull();
    }
}
