package com.dev.service;

import java.util.List;

import javax.management.relation.Role;

import org.springframework.http.ResponseEntity;

import com.dev.entity.Roles;

public interface RolesService {
    public ResponseEntity<Roles> saveRoles(Roles role);

    public ResponseEntity<List<Roles>> getAllRoles();

    public ResponseEntity<Roles> updateRole(Roles roles);

    public ResponseEntity<Void> deleteRole(Long id);
}
