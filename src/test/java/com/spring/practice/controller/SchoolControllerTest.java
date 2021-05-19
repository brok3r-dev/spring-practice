package com.spring.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.spring.practice.data.JwtDto;
import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.data.response.SchoolResponse;
import com.spring.practice.data.response.StudentResponse;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Student;
import com.spring.practice.service.impl.SchoolServiceImpl;
import com.spring.practice.service.impl.UserDetailsServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SchoolController.class)
class SchoolControllerTest {
    @MockBean
    PasswordEncoder encoder;
    @MockBean
    UserDetailsServiceImpl userDetailsService;
    @MockBean
    JwtDto jwtDto;
    @MockBean
    SchoolServiceImpl service;

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    private SchoolRequest request;
    private School school;

    @BeforeEach
    void setUp() {
        request = new SchoolRequest();
        request.setName("Hero Academia");
        request.setAddress("in Japan");
        request.setPhoneNumber("unknown");
        school = new School(request);
    }

    @Test
    @WithMockUser(authorities = "school:write")
    void canRegisterSchool() throws Exception {
        //region given
        String content = mapper.writeValueAsString(request);
        String response = mapper.writeValueAsString(new SchoolResponse(school));

        when(service.registerSchool(any())).thenReturn(school);
        //endregion

        //then
        mvc.perform(post("/school/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(authorities = "school:write")
    void canUpdateSchool() throws Exception {
        //region given
        String content = mapper.writeValueAsString(request);
        String response = mapper.writeValueAsString(new SchoolResponse(school));

        when(service.updateSchool(any())).thenReturn(school);
        //endregion

        //then
        mvc.perform(put("/school/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(authorities = "school:write")
    void canAlertAllStudents() throws Exception {
        //region given
        String content = mapper.writeValueAsString(request);
        when(service.alertAllStudents(request.getName())).thenReturn(true);
        //endregion

        //then
        mvc.perform(post("/school/alert")
                .content(content)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("true"));
    }

    @Test
    @WithMockUser(authorities = "school:read")
    void canFindAllSchools() throws Exception {
        //region given
        String response = mapper.writeValueAsString(Arrays.asList(new SchoolResponse(school), new SchoolResponse(school)));
        when(service.findAllSchools()).thenReturn(Arrays.asList(school, school));
        //endregion

        //then
        mvc.perform(get("/school/find/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canFindSchool() throws Exception {
        //region given
        String response = mapper.writeValueAsString(new SchoolResponse(school));
        when(service.findSchool("Hero Academia")).thenReturn(school);
        //endregion

        //then
        mvc.perform(get("/school/find/Hero Academia"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canGetAllStudents() throws Exception {
        //region given
        Student student = new Student();
        student.setName("Bakugo");
        student.setSchool(school);

        StudentResponse studentResponse = new StudentResponse(student);
        String response = mapper.writeValueAsString(Arrays.asList(studentResponse, studentResponse));

        when(service.getAllStudents("Hero Academia")).thenReturn(Arrays.asList(student, student));
        //endregion

        //then
        mvc.perform(get("/school/get/students/Hero Academia"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(authorities = "school:write")
    void canUnregisterSchool() throws Exception {
        //region given
        String response = mapper.writeValueAsString(new SchoolResponse(school));
        when(service.unregisterSchool("Hero Academia")).thenReturn(school);
        //endregion

        //then
        mvc.perform(delete("/school/delete/Hero Academia"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canNotFindApi() throws Exception {
        //then
        mvc.perform(get("/school/unknown/api"))
                .andExpect(status().isNotFound());
    }

    @Test
    void canBeDenied() throws Exception {
        //then
        mvc.perform(get("/school/find/all"))
                .andExpect(status().isForbidden());
    }
}