package com.dev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.RolesTaken;
import com.dev.repository.RolesTakenRepository;
import com.dev.service.RolesTakenService;
@Service
public class RolesTakenServiceImpl implements RolesTakenService {

    @Autowired
    RolesTakenRepository rolesTakenRepository;

    @Override
    public ResponseEntity<RolesTaken> saveRolesTaken(RolesTaken rolesTaken) {
        return null;
    }

    @Override
    public List<RolesTaken> getAllRoleTaken() {
        return rolesTakenRepository.findAll();
    }

    
}
