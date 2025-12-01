package com.sil.digitalbankingbackend.services;
import com.sil.digitalbankingbackend.dtos.EnrollmentDTO;
import com.sil.digitalbankingbackend.dtos.EnrollmentDetailsDTO;
import com.sil.digitalbankingbackend.dtos.EnrollmentRequest;
import com.sil.digitalbankingbackend.entities.Course;
import com.sil.digitalbankingbackend.entities.Enrollment;
import com.sil.digitalbankingbackend.entities.User;
import com.sil.digitalbankingbackend.repositories.CourseRepository;
import com.sil.digitalbankingbackend.repositories.EnrollmentRepository;
import com.sil.digitalbankingbackend.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    @Transactional(Transactional.TxType.REQUIRED)
    public ResponseEntity<String> enrollMultipleCourses(EnrollmentRequest request) {
        // Step 1: Find user
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        // Step 2: Prepare enrollments list
        List<Enrollment> enrollments = new ArrayList<>();

        // Step 3: Loop through course IDs and create Enrollment
        for (Integer courseCode : request.getCourseCodes()) {
            Course course = courseRepository.findByCourseCode(courseCode)
                    .orElseThrow(() -> new RuntimeException("Course not found with ID: " + courseCode));

            Enrollment enrollment = Enrollment.builder()
                    .user(user)
                    .course(course)
                    .enrolledAt(LocalDateTime.now())
                    .build();

            enrollments.add(enrollment);
        }

        // Step 4: Save all enrollments
        enrollmentRepository.saveAll(enrollments);

        // Step 5: Return success response
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("User successfully enrolled in " + enrollments.size() + " course(s)");
    }

    @Cacheable(
            value = "enrollmentCache",
            key = "T(String).valueOf(#userId) + '-' + T(String).valueOf(#categoryName) + '-' + #page"
    )
    public Page<EnrollmentDTO> getFilteredEnrollments(Long userId, String categoryName, int page, int size) {

        Pageable pageable = PageRequest.of(page, size, Sort.by("enrolledAt").descending());

        return enrollmentRepository.filterEnrollments(userId, categoryName, pageable);
    }



    public Page<EnrollmentDetailsDTO> filterEnrollments(
            Long userId,
            String categoryName,
            String courseTitle,
            int page,
            int size,
            String sort
    ) {
        // Sorting Logic
        String[] sortParams = sort.split(",");
        String sortField = sortParams[0];
        Sort.Direction direction = sortParams.length > 1 && sortParams[1].equalsIgnoreCase("desc")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortField));

        return enrollmentRepository.filterEnrollments(userId, categoryName, courseTitle, pageable);
    }



}