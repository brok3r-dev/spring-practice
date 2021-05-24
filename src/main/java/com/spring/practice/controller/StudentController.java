package com.spring.practice.controller;

import com.spring.practice.data.request.StudentRequest;
import com.spring.practice.data.response.StudentResponse;
import com.spring.practice.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<StudentResponse> registerStudent(
            @RequestBody @Validated StudentRequest studentRequest
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.registerStudent(studentRequest)));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<StudentResponse> updateStudent(
            @RequestBody @Validated StudentRequest studentRequest
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.updateStudent(studentRequest)));
    }

    @GetMapping("/find/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<StudentResponse>> findAllStudents() {
        return ResponseEntity.ok(studentService.findAllStudents().stream().map(StudentResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<StudentResponse>> findStudents(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(studentService.findStudents(name).stream().map(StudentResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/id/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<StudentResponse> findStudent(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.findStudent(id)));
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAuthority('student:write')")
    public ResponseEntity<StudentResponse> unregisterStudent(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(new StudentResponse(studentService.unregisterStudent(id)));
    }
}
