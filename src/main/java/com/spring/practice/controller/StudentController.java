package com.spring.practice.controller;

import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.data.response.StudentResponse;
import com.spring.practice.mapper.StudentMapper;
import com.spring.practice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAuthority('student:write')")
    ResponseEntity<?> registerStudent(
            @RequestBody @Validated StudentRequest studentRequest
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.registerStudent(studentRequest)));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('student:write')")
    ResponseEntity<?> updateStudent(
            @RequestBody @Validated StudentRequest studentRequest
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.updateStudent(studentRequest)));
    }

    @GetMapping("/find/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    ResponseEntity<?> findAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents().stream().map(StudentResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    ResponseEntity<?> findStudents(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(studentService.findStudents(name).stream().map(StudentResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/id/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    ResponseEntity<?> findStudent(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.findStudent(id)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    ResponseEntity<?> deleteStudent(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.deleteStudent(id)));
    }
}
