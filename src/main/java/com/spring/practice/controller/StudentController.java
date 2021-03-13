package com.spring.practice.controller;

import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.data.response.StudentResponse;
import com.spring.practice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @PostMapping("/register")
    ResponseEntity<?> postStudent(
            @RequestBody @Validated StudentRequest studentRequest
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.postStudent(studentRequest)));
    }

    @GetMapping("/find/all")
    ResponseEntity<?> getAllStudents() {
        return ResponseEntity.ok(studentService.getAllStudents().stream().map(StudentResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/{name}")
    ResponseEntity<?> getStudents(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(studentService.getStudents(name).stream().map(StudentResponse::new).collect(Collectors.toList()));
    }
}
