package com.spring.practice.controller;

import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.data.response.SchoolResponse;
import com.spring.practice.data.response.StudentResponse;
import com.spring.practice.mapper.SchoolMapper;
import com.spring.practice.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('school:write')")
    ResponseEntity<?> registerSchool(
            @RequestBody @Validated SchoolRequest schoolRequest
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.registerSchool(schoolRequest)));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('school:write')")
    ResponseEntity<?> updateSchool(
            @RequestBody @Validated SchoolRequest schoolRequest
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.updateSchool(schoolRequest)));
    }

    @PostMapping("/alert")
    @PreAuthorize("hasAuthority('school:write')")
    ResponseEntity<?> alertAllStudents(
            @RequestBody @Validated SchoolRequest schoolRequest
    ) {
        return ResponseEntity.ok(schoolService.alertAllStudents(schoolRequest.getName()));
    }

    @GetMapping("/find/all")
    @PreAuthorize("hasAuthority('school:read')")
    ResponseEntity<?> findAllSchools() {
        return ResponseEntity.ok(schoolService.findAllSchools().stream().map(SchoolResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    ResponseEntity<?> findSchool(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.findSchool(name)));
    }

    @GetMapping("/get/students/{name}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    ResponseEntity<?> getAllStudents(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(schoolService.getAllStudents(name).stream().map(StudentResponse::new));
    }

    @DeleteMapping("/delete/{name}")
    @PreAuthorize("hasAuthority('school:write')")
    ResponseEntity<?> deleteSchool(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.deleteSchool(name)));
    }
}
