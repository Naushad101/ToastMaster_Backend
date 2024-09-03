package com.dev.service;

import java.util.List;
import org.springframework.http.ResponseEntity;

import com.dev.entity.MemberShip;

public interface MembershipService {
    public ResponseEntity<MemberShip> saveMembership(MemberShip memberShip);

    public ResponseEntity<List<MemberShip>> getallMembershipDetails();
}
