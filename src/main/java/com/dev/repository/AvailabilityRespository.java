package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.Availability;

@Repository
public interface AvailabilityRespository extends JpaRepository<Availability,Long> {
    
}
