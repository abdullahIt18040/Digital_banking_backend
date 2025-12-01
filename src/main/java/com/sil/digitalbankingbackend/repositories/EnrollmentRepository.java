package com.sil.digitalbankingbackend.repositories;

import com.sil.digitalbankingbackend.dtos.EnrollmentDTO;
import com.sil.digitalbankingbackend.dtos.EnrollmentDetailsDTO;
import com.sil.digitalbankingbackend.entities.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EnrollmentRepository  extends JpaRepository<Enrollment,String> {

@Query("""

select new com.sil.digitalbankingbackend.dtos.EnrollmentDTO(concat(u.firstName,u.lastName),
                             c.title,
                              cat.name,
                              e.enrolledAt
                              ) 
                              from Enrollment   e
                              join e.user   u
                              join e.course c
                              join c.category cat
                              where (
                              
                              (:userId IS NULL or u.id = :userId)
                              AND (:categoryName IS null or lower(cat.name)like lower(concat('%', COALESCE(:categoryName, ''), '%')) )
                              )
                              
""")
  Page<EnrollmentDTO> filterEnrollments(@Param("userId")Long userId,
                                        @Param("categoryName")String categoryName,
                                        Pageable pageable);





  @Query("""
        SELECT new com.sil.digitalbankingbackend.dtos.EnrollmentDetailsDTO(
            u.id, u.firstName, u.email,
            c.id, c.title,
            cat.id, cat.name,
            e.enrolledAt
        )
        FROM Enrollment e
        JOIN e.user u
        JOIN e.course c
        JOIN c.category cat
        WHERE 
            (:userId IS NULL OR u.id = :userId)
            AND (:categoryName IS NULL OR LOWER(cat.name) LIKE LOWER(CONCAT('%', :categoryName, '%')))
            AND (:courseTitle IS NULL OR LOWER(c.title) LIKE LOWER(CONCAT('%', :courseTitle, '%')))
    """)
  Page<EnrollmentDetailsDTO> filterEnrollments(
          @Param("userId") Long userId,
          @Param("categoryName") String categoryName,
          @Param("courseTitle") String courseTitle,
          Pageable pageable
  );





}
