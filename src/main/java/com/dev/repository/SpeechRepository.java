package com.dev.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.Speech;

@Repository
public interface SpeechRepository extends JpaRepository<Speech,Long> {
    
}
