package com.dev.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dev.entity.RolesTaken;

public interface RolesTakenService {
    public ResponseEntity<RolesTaken> saveRolesTaken(RolesTaken rolesTaken);
    public List<RolesTaken> getAllRoleTaken();
}
