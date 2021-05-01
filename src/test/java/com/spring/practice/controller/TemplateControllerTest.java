package com.spring.practice.controller;

import com.spring.practice.data.JwtDto;
import com.spring.practice.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@WebMvcTest(TemplateController.class)
class TemplateControllerTest {
    @MockBean
    PasswordEncoder encoder;
    @MockBean
    UserDetailsServiceImpl service;
    @MockBean
    JwtDto jwtDto;

    @Autowired
    MockMvc mvc;

    @Test
    @WithMockUser(username = "user", password = "password")
    void canGetErrorPage() throws Exception {
        //then
        mvc.perform(get("/error"))
                .andExpect(status().isOk())
                .andExpect(view().name("error"));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canGetLoginPage() throws Exception {
        //then
        mvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canGetWelcomePage() throws Exception {
        //then
        mvc.perform(get("/welcome"))
                .andExpect(status().isOk())
                .andExpect(view().name("welcome"));
    }

    @Test
    void canBeDenied() throws Exception {
        //then
        mvc.perform(get("/welcome"))
                .andExpect(status().isForbidden());
    }
}