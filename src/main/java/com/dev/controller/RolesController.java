package com.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.Roles;
import com.dev.service.impl.RolesServiceImpl;

@RestController
@RequestMapping("api/role")
public class RolesController {

    @Autowired
    RolesServiceImpl rolesServiceImpl;
    
    @PostMapping()
    public ResponseEntity<Roles> saveRoles(@RequestBody Roles role){
        return rolesServiceImpl.saveRoles(role);
    }

    @GetMapping()
    public ResponseEntity<List<Roles>> getAllRoles(){
        return rolesServiceImpl.getAllRoles();
    }

    @PutMapping()
    public ResponseEntity<Roles> updateRole(@RequestBody Roles roles){
        return rolesServiceImpl.updateRole(roles);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteRole(@PathVariable() Long id){
        return rolesServiceImpl.deleteRole(id);
    }
}
