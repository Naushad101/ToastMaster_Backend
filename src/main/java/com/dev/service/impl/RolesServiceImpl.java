package com.dev.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.Roles;
import com.dev.exception.RoleNotFoundException;
import com.dev.repository.MemberDetailsRepository;
import com.dev.repository.RolesRepository;
import com.dev.service.RolesService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RolesServiceImpl implements RolesService{

    @Autowired
    MemberDetailsRepository memberDetailsRepository;

    @Autowired
    RolesRepository rolesRepository;

    @Override
    public ResponseEntity<Roles> saveRoles(Roles role) {
        return new ResponseEntity<>(rolesRepository.save(role),HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<List<Roles>> getAllRoles() {

        if(rolesRepository.findAll()==null){

            String message = "No roles present in database";

            log.error(message);

            throw new RoleNotFoundException(message);
        }

        return new ResponseEntity<>(rolesRepository.findAll(),HttpStatus.FOUND);
    }


    @Override
    public ResponseEntity<Roles> updateRole(Roles roles) {
        Optional<Roles> roleOptional = rolesRepository.findById(roles.getId());
        if(roleOptional.isEmpty()){
            String message = "Roles with id "+ roles.getId() + " is not present into database";
            log.error(message);
            throw new RoleNotFoundException(message);
        } 

        Roles existingRole = roleOptional.get();
        existingRole.setId(roles.getId());
        existingRole.setRoleName(roles.getRoleName());
        return new ResponseEntity<>(rolesRepository.save(existingRole),HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity<Void> deleteRole(Long id) {
            if(rolesRepository.findById(id).isEmpty()){
                String message = "Role with id "+ id + " is not present into database";
                log.error(message);
                throw new RoleNotFoundException(message);
            }

            rolesRepository.deleteById(id);

            log.info("Role with id {} has been deleted successfully", id);

            return ResponseEntity.noContent().build();
    }
    
}
