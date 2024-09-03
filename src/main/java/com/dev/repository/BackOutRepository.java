package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.BackOut;

@Repository
public interface BackOutRepository extends JpaRepository<BackOut,Long>{
    
}
