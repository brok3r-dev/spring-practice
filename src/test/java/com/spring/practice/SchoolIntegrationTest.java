package com.spring.practice;

import com.spring.practice.controller.SchoolController;
import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.data.response.SchoolResponse;
import com.spring.practice.data.response.StudentResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.jdbc.Sql;

import javax.transaction.Transactional;

import java.util.Comparator;
import java.util.List;

import static org.assertj.core.api.Assertions.as;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
public class SchoolIntegrationTest {
    @Autowired SchoolController controller;

    @Test
    @WithMockUser(authorities = "school:write")
    @Sql("/test.sql")
    void canRegisterSchool() {
        //given
        SchoolRequest request = new SchoolRequest("Test School", "in Seoul", "02-000-0000");

        //when
        SchoolResponse response = controller.registerSchool(request).getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getAddress()).isEqualTo(request.getAddress());
        assertThat(response.getPhoneNumber()).isEqualTo(request.getPhoneNumber());
    }

    @Test
    @WithMockUser(authorities = "school:write")
    @Sql("/test.sql")
    void canUpdateSchool() {
        //given
        SchoolRequest request = new SchoolRequest("JeonBuk University", "in Gwangju", "053-000-0000");

        //when
        SchoolResponse response = controller.updateSchool(request).getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo(request.getName());
        assertThat(response.getAddress()).isEqualTo(request.getAddress());
        assertThat(response.getPhoneNumber()).isEqualTo(request.getPhoneNumber());
    }

    @Test
    @WithMockUser(authorities = "school:write")
    @Sql("/test.sql")
    void canAlertAllStudents() {
        //given
        SchoolRequest request = new SchoolRequest("Seoul University", "in Seoul", "02-000-0000");

        //when
        Boolean response = controller.alertAllStudents(request).getBody();

        //then
        assertThat(response).isTrue();
    }

    @Test
    @WithMockUser(authorities = "school:read")
    @Sql("/test.sql")
    void canFindAllSchools() {
        //when
        List<SchoolResponse> response = controller.findAllSchools().getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.size()).isGreaterThan(0);
        assertThat(response.get(0).getName()).isEqualTo("Seoul University");
        assertThat(response.get(0).getAddress()).isEqualTo("in Seoul");
        assertThat(response.get(0).getPhoneNumber()).isEqualTo("02-000-0000");
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    @Sql("/test.sql")
    void canFindSchool() {
        //when
        SchoolResponse response = controller.findSchool("Seoul University").getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("Seoul University");
        assertThat(response.getAddress()).isEqualTo("in Seoul");
        assertThat(response.getPhoneNumber()).isEqualTo("02-000-0000");
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    @Sql("/test.sql")
    void canGetAllStudents() {
        //when
        List<StudentResponse> response = controller.getAllStudents("Busan University").getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.size()).isGreaterThan(0);
        response.sort((o1, o2) -> (int) (o1.getId() - o2.getId()));
        assertThat(response.get(0).getName()).isEqualTo("Student D");
        assertThat(response.get(0).getAddress()).isEqualTo("in Busan");
        assertThat(response.get(0).getPhoneNumber()).isEqualTo("051-000-0000");
    }

    @Test
    @WithMockUser(authorities = "school:write")
    @Sql("/test.sql")
    void canUnregisterSchool() {
        //when
        SchoolResponse response = controller.unregisterSchool("JeonBuk University").getBody();

        //then
        assertThat(response).isNotNull();
        assertThat(response.getName()).isEqualTo("JeonBuk University");
        assertThat(response.getAddress()).isEqualTo("in Iksan");
        assertThat(response.getPhoneNumber()).isEqualTo("063-000-0000");
    }
}
