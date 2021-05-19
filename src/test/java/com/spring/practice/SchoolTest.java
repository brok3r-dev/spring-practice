package com.spring.practice;

import com.spring.practice.entity.School;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { TestConfig.class })
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/test.sql")
public class SchoolTest {
    @Autowired
    private TestRestTemplate template;

    @LocalServerPort
    private Integer port;

    @Test
    void canRegisterSchool() {
    }

    @Test
    void canUpdateSchool() {
    }

    @Test
    void canAlertAllStudents() {
    }

    @Test
    void canFindAllSchools() {
    }

    @Test
    void canFindSchool() {
        //given
        String url = "http://localhost:" + port +  "/school/find/Seoul University";

        //when
        ResponseEntity<School> school = template.getForEntity(url, School.class);

        //then
        assertThat(school.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(school.getBody().getId()).isEqualTo(1);
        assertThat(school.getBody().getName()).isEqualTo("Seoul University");
        assertThat(school.getBody().getAddress()).isEqualTo("in Seoul");
        assertThat(school.getBody().getPhoneNumber()).isEqualTo("02-000-0000");
    }

    @Test
    void canGetAllStudents() {
    }

    @Test
    void canUnregisterSchool() {
    }

    @Test
    void canBeDenied() {
        //when
        ResponseEntity<School> school = template.getForEntity("http://localhost:" + port +  "/school/find/Seoul University", School.class);

        //then
        assertThat(school.getStatusCode()).isEqualTo(HttpStatus.UNAUTHORIZED);
    }
}
