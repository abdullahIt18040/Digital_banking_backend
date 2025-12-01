package com.sil.digitalbankingbackend.dtos;


import lombok.Data;
import java.util.List;

@Data
public class EnrollmentRequest {
    private Long userId;
    private List<Integer> courseCodes;
}
