package com.spring.practice.repository;

import com.spring.practice.entity.School;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class SchoolRepositoryTest {
    @Autowired
    SchoolRepository schoolRepository;

    static List<School> schools;

    @BeforeAll
    static void init() {
        schools = new ArrayList<>(Arrays.asList(
                new School("Test A School", "in Seoul", "02-123-4567"),
                new School("Test B School", "in Busan", "051-123-4567"),
                new School("Test C School", "in Daegoo", "053-123-4567")
        ));
    }

    @BeforeEach
    void setUp() {
        for (School s : schools) {
            schoolRepository.save(s);
        }
    }

    @AfterEach
    void tearDown() {
        schoolRepository.deleteAll();
    }

    @Test
    void canSaveSchool() {
        //given
        School school = new School("Test D School", "in U.S.A", "+1");

        //when
        School savedSchool = schoolRepository.save(school);

        //then
        assertThat(savedSchool).isEqualTo(school);
    }

    @Test
    void canFindSchoolByName() {
        //given
        String nameA = "Test A School";
        String nameB = "Test B School";
        String nameC = "Test C School";

        //when
        School schoolA = schoolRepository.findByName(nameA);
        School schoolB = schoolRepository.findByName(nameB);
        School schoolC = schoolRepository.findByName(nameC);

        //then
        assertThat(schoolA.getName()).isEqualTo(nameA);
        assertThat(schoolB.getName()).isEqualTo(nameB);
        assertThat(schoolC.getName()).isEqualTo(nameC);
    }

    @Test
    void canNotFindSchoolByName() {
        //given
        String name = "Ghost School";

        //when
        School school = schoolRepository.findByName(name);

        //then
        assertThat(school).isNull();
    }
}