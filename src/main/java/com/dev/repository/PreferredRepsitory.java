package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.PreferredRoles;

@Repository
public interface PreferredRepsitory extends JpaRepository<PreferredRoles,Long> {
    
}
