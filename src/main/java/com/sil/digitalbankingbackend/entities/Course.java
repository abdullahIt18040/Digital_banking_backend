package com.sil.digitalbankingbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Table(indexes = @Index(columnList = "title", name = "idx_course_title"))
@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;
   private String title;
   private Integer courseCode;
   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "category_id")
   private Category category;


}
