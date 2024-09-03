package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.MemberShip;

@Repository
public interface MembershipRepository extends JpaRepository<MemberShip,Long> {
    
}
