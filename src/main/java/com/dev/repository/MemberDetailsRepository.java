package com.dev.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.MemberDetails;

@Repository
public interface MemberDetailsRepository extends JpaRepository<MemberDetails,Long> {
    public Optional<MemberDetails> findByFirstName(String name);
}
