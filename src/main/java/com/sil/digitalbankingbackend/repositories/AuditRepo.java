package com.sil.digitalbankingbackend.repositories;

import com.sil.digitalbankingbackend.entities.AuditUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuditRepo extends JpaRepository<AuditUser,Integer> {

}
