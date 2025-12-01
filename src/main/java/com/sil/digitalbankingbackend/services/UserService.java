package com.sil.digitalbankingbackend.services;

import com.sil.digitalbankingbackend.entities.AuditUser;
import com.sil.digitalbankingbackend.entities.User;
import com.sil.digitalbankingbackend.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserAuditService userAuditService;
    @Autowired
    private UserRepository userRepository;
    @Transactional(propagation = Propagation.REQUIRED)
    public void save(User user)
    {
        userRepository.save(user);
        userAuditService.audit(new AuditUser(user,"READ"));
    }

}
