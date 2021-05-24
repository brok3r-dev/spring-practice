package com.spring.practice.controller;

import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.data.response.SchoolResponse;
import com.spring.practice.data.response.StudentResponse;
import com.spring.practice.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/school")
public class SchoolController {
    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {
        this.schoolService = schoolService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('school:write')")
    public ResponseEntity<SchoolResponse> registerSchool(
            @RequestBody @Validated SchoolRequest schoolRequest
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.registerSchool(schoolRequest)));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('school:write')")
    public ResponseEntity<SchoolResponse> updateSchool(
            @RequestBody @Validated SchoolRequest schoolRequest
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.updateSchool(schoolRequest)));
    }

    @PostMapping("/alert")
    @PreAuthorize("hasAuthority('school:write')")
    public ResponseEntity<Boolean> alertAllStudents(
            @RequestBody @Validated SchoolRequest schoolRequest
    ) {
        return ResponseEntity.ok(schoolService.alertAllStudents(schoolRequest.getName()));
    }

    @GetMapping("/find/all")
    @PreAuthorize("hasAuthority('school:read')")
    public ResponseEntity<List<SchoolResponse>> findAllSchools() {
        return ResponseEntity.ok(schoolService.findAllSchools().stream().map(SchoolResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<SchoolResponse> findSchool(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.findSchool(name)));
    }

    @GetMapping("/get/students/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public ResponseEntity<List<StudentResponse>> getAllStudents(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(schoolService.getAllStudents(name).stream().map(StudentResponse::new).collect(Collectors.toList()));
    }

    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasAuthority('school:write')")
    public ResponseEntity<SchoolResponse> unregisterSchool(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.unregisterSchool(name)));
    }
}
