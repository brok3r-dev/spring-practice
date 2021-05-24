package com.spring.practice.controller;

import com.spring.practice.data.request.TeacherRequest;
import com.spring.practice.data.response.TeacherResponse;
import com.spring.practice.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    private final TeacherService teacherService;

    @Autowired
    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAuthority('teacher:write')")
    public ResponseEntity<TeacherResponse> registerTeacher(
            @RequestBody @Validated TeacherRequest teacherRequest
    ) {
        return ResponseEntity.ok(new TeacherResponse(teacherService.registerTeacher(teacherRequest)));
    }

    @PutMapping("/update")
    @PreAuthorize("hasAuthority('teacher:write')")
    public ResponseEntity<TeacherResponse> updateTeacher(
            @RequestBody @Validated TeacherRequest teacherRequest
    ) {
        return ResponseEntity.ok(new TeacherResponse(teacherService.updateTeacher(teacherRequest)));
    }

    @GetMapping("/find/all")
    @PreAuthorize("hasAuthority('teacher:read')")
    public ResponseEntity<List<TeacherResponse>> findAllTeachers() {
        return ResponseEntity.ok(teacherService.findAllTeacher().stream().map(TeacherResponse::new).collect(Collectors.toList()));
    }

    @GetMapping("/find")
    @PreAuthorize("hasAuthority('teacher:read')")
    public ResponseEntity<TeacherResponse> findTeacher(
            @RequestParam("name") String name,
            @RequestParam("schoolId") Long schoolId
    ) {
        return ResponseEntity.ok(new TeacherResponse(teacherService.findTeacher(name, schoolId)));
    }

    @DeleteMapping("/unregister/{id}")
    @PreAuthorize("hasAuthority('teacher:write')")
    public ResponseEntity<TeacherResponse> unregisterTeacher(
            @PathVariable("id") Long id
    ) {
        return ResponseEntity.ok(new TeacherResponse(teacherService.unregisterTeacher(id)));
    }
}
