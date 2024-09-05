package com.dev.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import com.dev.dto.MemberDTO;
import com.dev.entity.MemberDetails;

public interface MemberDetailsService {
    
    public ResponseEntity<MemberDTO> saveMemberDetails(MemberDTO memberDTO);

    public List<MemberDetails> getAllMemberDetails();

    public ResponseEntity<MemberDetails> updateMemberDetails(MemberDetails memberDetails);

    public ResponseEntity<Void> deleteMemberById(Long id);
}
