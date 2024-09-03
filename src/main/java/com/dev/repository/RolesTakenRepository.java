package com.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.RolesTaken;

@Repository
public interface RolesTakenRepository extends JpaRepository<RolesTaken,Long> {

    public Optional<RolesTaken> findByRoleName(String roleName);
}
