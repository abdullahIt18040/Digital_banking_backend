package com.sil.digitalbankingbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrollmentDTO {
    private String userName;
    private String courseTitle;
    private String categoryName;
    private LocalDateTime enrolledAt;
}
