package com.sil.digitalbankingbackend.services;

import com.sil.digitalbankingbackend.entities.AuditUser;
import com.sil.digitalbankingbackend.repositories.AuditRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserAuditService {
    @Autowired
    private AuditRepo auditRepo;
    @Transactional(propagation = Propagation.MANDATORY)
    public void audit(AuditUser auditUser)
    {
        auditRepo.save(auditUser);

    }

}
