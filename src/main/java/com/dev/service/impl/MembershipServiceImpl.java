package com.dev.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.MemberDetails;
import com.dev.entity.MemberShip;
import com.dev.exception.MemberDetailsNotFoundException;
import com.dev.exception.MembershipDetailsNotFoundException;
import com.dev.repository.MemberDetailsRepository;
import com.dev.repository.MembershipRepository;
import com.dev.service.MembershipService;

@Service
public class MembershipServiceImpl implements MembershipService {

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
    MemberDetailsRepository memberDetailsRepository;

    @Override
    public ResponseEntity<MemberShip> saveMembership(MemberShip memberShip) {

        Optional<MemberDetails> memOptional = memberDetailsRepository.findById(memberShip.getMemberDetails().getId());

        if(memOptional.isEmpty()){
            throw new MemberDetailsNotFoundException("Member details with id "+memberShip.getMemberDetails().getId()+" is not present in database");
        }

        memberShip.setMemberDetails(memOptional.get());

        return new ResponseEntity<>(membershipRepository.save(memberShip),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<MemberShip>> getallMembershipDetails() {
        List<MemberShip> memberShips = membershipRepository.findAll();
        if(memberShips==null){
            throw new MembershipDetailsNotFoundException("MembershipDetails is not present in database");
        }

        return new ResponseEntity<>(memberShips,HttpStatus.ACCEPTED);
    }
    
}
