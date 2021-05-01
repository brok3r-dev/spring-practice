package com.spring.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.practice.common.enums.Grade;
import com.spring.practice.data.JwtDto;
import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.data.response.StudentResponse;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Student;
import com.spring.practice.service.impl.StudentServiceImpl;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(StudentController.class)
class StudentControllerTest {
    @MockBean
    PasswordEncoder encoder;
    @MockBean
    UserDetailsServiceImpl userDetailsService;
    @MockBean
    JwtDto jwtDto;
    @MockBean
    StudentServiceImpl service;

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    private StudentRequest request;
    private School school;
    private Student student;

    @BeforeEach
    void setUp() {
        request = new StudentRequest();
        request.setName("Bakugo");
        request.setAddress("in Japan");
        request.setPhoneNumber("unknown");
        request.setGrade(Grade.H_FIRST);
        request.setFcmToken("NEKOT TERCES");

        school = new School();
        school.setId(20L);
        school.setName("Hero Academia");

        student = new Student(request, school);
    }

    @Test
    @WithMockUser(authorities = "student:write")
    void canRegisterStudent() throws Exception {
        //region given
        String content = mapper.writeValueAsString(request);
        String response = mapper.writeValueAsString(new StudentResponse(student));

        when(service.registerStudent(any())).thenReturn(student);
        //endregion

        //then
        mvc.perform(post("/student/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(authorities = "student:write")
    void canUpdateStudent() throws Exception {
        //region given
        String content = mapper.writeValueAsString(request);
        String response = mapper.writeValueAsString(new StudentResponse(student));

        when(service.updateStudent(any())).thenReturn(student);
        //endregion

        //then
        mvc.perform(put("/student/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canFindAllStudents() throws Exception {
        //region given
        String response = mapper.writeValueAsString(Arrays.asList(new StudentResponse(student), new StudentResponse(student)));

        when(service.findAllStudents()).thenReturn(Arrays.asList(student, student));
        //endregion

        //then
        mvc.perform(get("/student/find/all"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canFindStudents() throws Exception {
        //region given
        String response = mapper.writeValueAsString(Arrays.asList(new StudentResponse(student), new StudentResponse(student)));

        when(service.findStudents("Bakugo")).thenReturn(Arrays.asList(student, student));
        //endregion

        //then
        mvc.perform(get("/student/find/Bakugo"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(username = "user", password = "password")
    void canFindStudent() throws Exception {
        //region given
        String response = mapper.writeValueAsString(new StudentResponse(student));

        when(service.findStudent(1L)).thenReturn(student);
        //endregion

        //then
        mvc.perform(get("/student/find/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    @WithMockUser(authorities = "student:write")
    void canUnregisterStudent() throws Exception {
        //region given
        String response = mapper.writeValueAsString(new StudentResponse(student));

        when(service.unregisterStudent(1L)).thenReturn(student);
        //endregion

        //then
        mvc.perform(delete("/student/delete/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(response));
    }

    @Test
    void canBeDenied() throws Exception {
        //then
        mvc.perform(get("/student/find/all"))
                .andExpect(status().isForbidden());
    }
}