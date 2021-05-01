package com.spring.practice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.practice.common.enums.Grade;
import com.spring.practice.data.JwtDto;
import com.spring.practice.data.request.TeacherRequest;
import com.spring.practice.data.response.TeacherResponse;
import com.spring.practice.entity.School;
import com.spring.practice.entity.Teacher;
import com.spring.practice.service.TeacherService;
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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.HashMap;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TeacherController.class)
class TeacherControllerTest {
    @MockBean
    PasswordEncoder passwordEncoder;
    @MockBean
    UserDetailsServiceImpl userDetailsService;
    @MockBean
    JwtDto jwtDto;
    @MockBean
    TeacherService service;

    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;

    private TeacherRequest request;
    private Teacher teacher;

    @BeforeEach
    void setUp() {
        request = new TeacherRequest();
        request.setName("All Might");
        request.setAddress("in Japan");
        request.setGrade(Grade.H_FIRST);
        request.setPhoneNumber("unknown");
        request.setFcmToken("NEKOT TERCES");

        School school = new School();
        school.setId(24L);
        school.setName("UA High School");

        teacher = new Teacher(request, school);
    }

    @Test
    @WithMockUser(authorities = "teacher:write")
    void canRegisterTeacher() throws Exception {
        //region given
        String content = mapper.writeValueAsString(request);
        String response = mapper.writeValueAsString(new TeacherResponse(teacher));

        when(service.registerTeacher(any())).thenReturn(teacher);
        //endregion

        //then
        mvc.perform(post("/teacher/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(print());
    }

    @Test
    @WithMockUser(authorities = "teacher:write")
    void canUpdateTeacher() throws Exception {
        //region given
        String content = mapper.writeValueAsString(request);
        String response = mapper.writeValueAsString(new TeacherResponse(teacher));

        when(service.updateTeacher(any())).thenReturn(teacher);
        //endregion

        //then
        mvc.perform(put("/teacher/update")
                .contentType(MediaType.APPLICATION_JSON)
                .content(content))
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(print());
    }

    @Test
    @WithMockUser(authorities = "teacher:read")
    void canGetAllTeachers() throws Exception {
        //region given
        String response = mapper.writeValueAsString(Arrays.asList(new TeacherResponse(teacher), new TeacherResponse(teacher)));

        when(service.findAllTeacher()).thenReturn(Arrays.asList(teacher, teacher));
        //endregion

        //then
        mvc.perform(get("/teacher/find/all"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].name").value(teacher.getName()))
                .andExpect(content().string(response))
                .andDo(print());
    }

    @Test
    @WithMockUser(authorities = "teacher:read")
    void canGetTeacher() throws Exception {
        //region given
        String response = mapper.writeValueAsString(new TeacherResponse(teacher));
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("name", "All Might");
        params.add("schoolId", "1");

        when(service.findTeacher("All Might", 1L)).thenReturn(teacher);
        //endregion

        //then
        mvc.perform(get("/teacher/find")
                .contentType(MediaType.APPLICATION_JSON)
                .params(params))
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(print());
    }

    @Test
    @WithMockUser(authorities = "teacher:write")
    void canUnregisterTeacher() throws Exception {
        //region given
        String response = mapper.writeValueAsString(new TeacherResponse(teacher));

        when(service.unregisterTeacher(1L)).thenReturn(teacher);
        //endregion

        //then
        mvc.perform(delete("/teacher/unregister/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(response))
                .andDo(print());
    }

    @Test
    void canBeDenied() throws Exception {
        //then
        mvc.perform(get("/teacher/find/all"))
                .andExpect(status().isForbidden())
                .andDo(print());
    }
}