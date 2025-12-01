package com.sil.digitalbankingbackend.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "users", indexes = {
        @Index(columnList = "email", name = "idx_user_email")
})
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;

    public User(String lastName, String email, String password, String firstName) {
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.firstName = firstName;
    }

    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    private String password;


    private String role = "USER";
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Enrollment> enrollments;

}

