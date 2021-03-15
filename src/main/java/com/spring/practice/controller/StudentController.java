package com.spring.practice.controller;

import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.data.response.StudentResponse;
import com.spring.practice.mapper.StudentMapper;
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

    @Autowired
    private StudentMapper studentMapper;

    @PostMapping("/register")
    ResponseEntity<?> registerStudent(
            @RequestBody @Validated StudentRequest studentRequest
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.registerStudent(studentRequest)));
    }

    @PostMapping("/update")
    ResponseEntity<?> updateStudent(
            @RequestBody @Validated StudentRequest studentRequest
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.updateStudent(studentRequest)));
    }

    @GetMapping("/find/all")
    ResponseEntity<?> findAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents().stream().map(StudentResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/{name}")
    ResponseEntity<?> findStudents(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(studentService.findStudents(name).stream().map(StudentResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/id/{id}")
    ResponseEntity<?> findStudent(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.findStudent(id)));
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> deleteStudent(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.deleteStudent(id)));
    }
}
