package com.spring.practice.controller;

import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.data.response.SchoolResponse;
import com.spring.practice.data.response.StudentResponse;
import com.spring.practice.mapper.SchoolMapper;
import com.spring.practice.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/register")
    ResponseEntity<?> registerSchool(
            @RequestBody @Validated SchoolRequest schoolRequest
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.registerSchool(schoolRequest)));
    }

    @PostMapping("/update")
    ResponseEntity<?> updateSchool(
            @RequestBody @Validated SchoolRequest schoolRequest
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.updateSchool(schoolRequest)));
    }

    @GetMapping("/find/all")
    ResponseEntity<?> findAllSchools() {
        return ResponseEntity.ok(schoolService.findAllSchools().stream().map(SchoolResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/{name}")
    ResponseEntity<?> findSchool(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.findSchool(name)));
    }

    @GetMapping("/get/students/{name}")
    ResponseEntity<?> getAllStudents(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(schoolService.getAllStudents(name).stream().map(StudentResponse::new));
    }

    @DeleteMapping("/delete/{name}")
    ResponseEntity<?> deleteSchool(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.deleteSchool(name)));
    }
}
