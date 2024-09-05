package com.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.RolesTaken;
import com.dev.service.impl.RolesTakenServiceImpl;

@RestController
@RequestMapping("api/rolestaken")
public class RolesTakenController {

    @Autowired
    RolesTakenServiceImpl rolesTakenServiceImpl;

    @PostMapping()
    public ResponseEntity<RolesTaken> saveRolesTaken(@RequestBody RolesTaken rolesTaken){
        return null;
    }

    @GetMapping()
    public List<RolesTaken> getAllRoleTaken(){
        return rolesTakenServiceImpl.getAllRoleTaken();
    }

    @DeleteMapping("{rolname}")
    public ResponseEntity<Void> deleteRoleAndMember(@PathVariable String roleName){
        return null;
    }
}
