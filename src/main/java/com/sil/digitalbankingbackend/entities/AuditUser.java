package com.sil.digitalbankingbackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor

public class AuditUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    private User user;
    private String permission;

    public AuditUser(User user, String permission) {
        this.user = user;
        this.permission = permission;
    }
}
