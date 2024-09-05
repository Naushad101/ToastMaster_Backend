package com.dev.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.dto.MemberDTO;
import com.dev.entity.MemberDetails;
import com.dev.service.impl.MemberDetailsServiceImpl;

import jakarta.mail.MessagingException;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("api/member")
@Slf4j
public class MemberDetailsController {

    private MemberDetailsServiceImpl memberDetailsService;


    public MemberDetailsController(MemberDetailsServiceImpl memberDetailsService) {
        this.memberDetailsService = memberDetailsService;
    }

    @PostMapping()
    public ResponseEntity<MemberDTO> saveMemberDetails(@RequestBody MemberDTO memberDTO) {
        // Save the member details
        ResponseEntity<MemberDTO> response = memberDetailsService.saveMemberDetails(memberDTO);

        // Example condition: Send email only if the email address is not empty
        if (memberDTO.getEmailAddress() != null && !memberDTO.getEmailAddress().isEmpty()) {
            try {
                log.info("calling");
                memberDetailsService.sendRegistrationEmail(memberDTO.getEmailAddress(), memberDTO.getFirstName());
                log.info("done");
            } catch (MessagingException e) {
                // Log the exception (or handle it in another way)
                System.err.println("Failed to send registration email: " + e.getMessage());
            }
        }

        return response;
    }
    
      


    // @PostMapping()
    // public ResponseEntity<MemberDTO> saveMemberDetails(@RequestBody MemberDTO memberDTO){

    //     return memberDetailsService.saveMemberDetails(memberDTO);
    // }

    @GetMapping("/memberDetails")
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
