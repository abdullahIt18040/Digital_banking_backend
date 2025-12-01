package com.sil.digitalbankingbackend.controllers;

import com.sil.digitalbankingbackend.dtos.EnrollmentDTO;
import com.sil.digitalbankingbackend.dtos.EnrollmentDetailsDTO;
import com.sil.digitalbankingbackend.dtos.EnrollmentRequest;
import com.sil.digitalbankingbackend.services.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    @PostMapping("/studentenrollCourses")
    public ResponseEntity<String> enrollStudent(@RequestBody EnrollmentRequest request) {
        return enrollmentService.enrollMultipleCourses(request);
    }


    @GetMapping("/filter")
    public ResponseEntity<Page<EnrollmentDTO>> filterEnrollments(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String categoryName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        Page<EnrollmentDTO> result = enrollmentService.getFilteredEnrollments(userId, categoryName, page, size);
        return ResponseEntity.ok(result);
    }




    @GetMapping("/filterWithPagination")
    public ResponseEntity<Page<EnrollmentDetailsDTO>> filterEnrollments(
            @RequestParam(required = false) Long userId,
            @RequestParam(required = false) String categoryName,
            @RequestParam(required = false) String courseTitle,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "enrolledAt,desc") String sort
    ) {
        Page<EnrollmentDetailsDTO> results = enrollmentService.filterEnrollments(
                userId, categoryName, courseTitle, page, size, sort
        );
        return ResponseEntity.ok(results);
    }








}
