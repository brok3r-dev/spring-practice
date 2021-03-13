package com.spring.practice.controller;

import com.spring.practice.data.request.SchoolRequest;
import com.spring.practice.data.response.SchoolResponse;
import com.spring.practice.service.SchoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;

    @PostMapping("/register")
    ResponseEntity<?> postSchool(
            @RequestBody @Validated SchoolRequest schoolRequest
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.postSchool(schoolRequest)));
    }

    @GetMapping("/find/all")
    ResponseEntity<?> getAllSchools() {
        return ResponseEntity.ok(schoolService.getAllSchools().stream().map(SchoolResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find/{name}")
    ResponseEntity<?> getSchool(
            @PathVariable("name") String name
    ) {
        return ResponseEntity.ok(new SchoolResponse(schoolService.getSchool(name)));
    }
}
