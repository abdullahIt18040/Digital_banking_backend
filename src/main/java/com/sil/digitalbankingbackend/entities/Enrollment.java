package com.sil.digitalbankingbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
@Data               // generates getters/setters/toString/equals/hashCode
@Builder
@Entity
@Table(indexes = {
        @Index(columnList = "user_id", name = "idx_enrollment_user"),
        @Index(columnList = "course_id", name = "idx_enrollment_course")
})
@NoArgsConstructor  // required by JPA
@AllArgsConstructor
public class Enrollment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private LocalDateTime enrolledAt;
//    @OneToMany(mappedBy = "course")
//    private List<Enrollment> enrollments;
}