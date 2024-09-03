package com.dev.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.MemberDetails;
import com.dev.service.impl.MemberDetailsServiceImpl;

@RestController
@RequestMapping("/api/user")
public class MemberDetailsController {

    private MemberDetailsServiceImpl memberDetailsService;


    public MemberDetailsController(MemberDetailsServiceImpl memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }


    @PostMapping()
    public ResponseEntity<MemberDetails> saveMemberDetails(@RequestBody MemberDetails memberDetails){
        return memberDetailsService.saveMemberDetails(memberDetails);
    }

    @GetMapping()
    public List<MemberDetails> getAllMemberDetails(){
        return memberDetailsService.getAllMemberDetails();
    }

    @PutMapping()
    public ResponseEntity<MemberDetails> updateMemberDetails(@RequestBody MemberDetails memberDetails){
        return memberDetailsService.updateMemberDetails(memberDetails);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMemberById(@PathVariable("id") Long id){
            return memberDetailsService.deleteMemberById(id);
    }
}
