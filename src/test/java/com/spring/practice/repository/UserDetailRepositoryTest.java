package com.spring.practice.repository;

import com.spring.practice.entity.UserDetail;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class UserDetailRepositoryTest {
    @Autowired
    UserDetailRepository userDetailRepository;

    static List<UserDetail> users;
    static String nameA = "test a";
    static String nameB = "test b";

    @BeforeAll
    static void beforeAll() {
        users = new ArrayList<>(Arrays.asList(
                new UserDetail(nameA),
                new UserDetail(nameB)
        ));
    }

    @BeforeEach
    void setUp() {
        for (UserDetail user : users) {
            userDetailRepository.save(user);
        }
    }

    @AfterEach
    void tearDown() {
        userDetailRepository.deleteAll();
    }

    @Test
    void canSaveUser() {
        //given
        String name = "test c";
        UserDetail user = new UserDetail(name);

        //when
        UserDetail savedUser = userDetailRepository.save(user);

        //then
        assertThat(savedUser).isEqualTo(user);
    }

    @Test
    void canFindByUsername() {
        //given
        String name = "test a";

        //when
        UserDetail user = userDetailRepository.findByUsername(name);

        //then
        assertThat(user.getUsername()).isEqualTo(name);
    }

    @Test
    void canNotFindByUsername() {
        //given
        String name = "test d";

        //when
        UserDetail user = userDetailRepository.findByUsername(name);

        //then
        assertThat(user).isNull();
    }
}