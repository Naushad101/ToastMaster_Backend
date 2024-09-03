package com.dev.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.RolesTaken;

@RestController
@RequestMapping("api/rolestaken")
public class RolesTakenController {
    
    @PostMapping()
    public ResponseEntity<RolesTaken> saveRolesTaken(@RequestBody RolesTaken rolesTaken){
        return null;
    }

    @DeleteMapping("{rolname}")
    public ResponseEntity<Void> deleteRoleAndMember(@PathVariable String roleName){
        return null;
    }
}
