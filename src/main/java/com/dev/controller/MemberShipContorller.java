package com.dev.controller;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.MemberShip;
import com.dev.service.impl.MembershipServiceImpl;

@RestController
@RequestMapping("api/membership")
public class MemberShipContorller {

    @Autowired
    MembershipServiceImpl membershipServiceImpl;

    @PostMapping()
    public ResponseEntity<MemberShip> saveMembership(@RequestBody MemberShip memberShip){
        return membershipServiceImpl.saveMembership(memberShip);
    }

    public ResponseEntity<List<MemberShip>> getallMembershipDetails(){
            return null;
    }
}
