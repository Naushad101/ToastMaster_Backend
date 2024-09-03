package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.MeetingDetails;

@Repository
public interface MeetingDetailsRepository extends JpaRepository<MeetingDetails,Long> {
    
}
