package com.dev.service;

import org.springframework.http.ResponseEntity;

import com.dev.entity.RolesTaken;

public interface RolesTakenService {
    public ResponseEntity<RolesTaken> saveRolesTaken(RolesTaken rolesTaken);
}
