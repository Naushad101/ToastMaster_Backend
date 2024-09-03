package com.dev.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.MemberDetails;

public interface MemberDetailsService {
    
    public ResponseEntity<MemberDetails> saveMemberDetails(MemberDetails memberDetails);

    public List<MemberDetails> getAllMemberDetails();

    public ResponseEntity<MemberDetails> updateMemberDetails(MemberDetails memberDetails);

    public ResponseEntity<Void> deleteMemberById(Long id);
}
