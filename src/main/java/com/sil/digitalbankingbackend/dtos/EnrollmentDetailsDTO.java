package com.sil.digitalbankingbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class EnrollmentDetailsDTO {
    private Long userId;
    private String userName;
    private String userEmail;
    private Long courseId;
    private String courseTitle;
    private Long categoryId;
    private String categoryName;
    private LocalDateTime enrolledAt;
}