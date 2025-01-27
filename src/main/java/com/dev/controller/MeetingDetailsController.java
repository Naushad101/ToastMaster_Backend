package com.dev.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.dev.entity.MeetingDetails;
import com.dev.service.impl.MeetingDetailsServiceImpl;

import jakarta.mail.MessagingException;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("api/meeting")
public class MeetingDetailsController {

    @Autowired
    MeetingDetailsServiceImpl meetingDetailsServiceImpl;

    @PostMapping()
    public ResponseEntity<MeetingDetails> saveMeetingDetails(@RequestBody MeetingDetails meetingDetails) throws MessagingException{
        return meetingDetailsServiceImpl.saveMeetingDetails(meetingDetails);
    }

    
    // @PostMapping()
    // public ResponseEntity<MeetingDetails> saveMeetingDetails(@RequestBody MeetingDetails meetingDetails) throws MessagingException {
    //     // Save meeting details
    //     ResponseEntity savedMeetingDetails = meetingDetailsServiceImpl.saveMeetingDetails(meetingDetails);

    //     // Extract relevant parameters for email
    //     String theme = savedMeetingDetails.getTheme();
    //     String venue = savedMeetingDetails.getVenue();
    //     String dateTime = savedMeetingDetails.getDateTime().toString(); // Convert to string if needed


    //     // Send email with specified details
    //     meetingDetailsServiceImpl.sendMeetingDetailEmail(theme, venue, dateTime);

    //     // Return response entity with saved meeting details
    //     return ResponseEntity.ok(savedMeetingDetails);
    // }




    // private void sendMeetingDetailEmail(String theme, String venue, String dateTime) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'sendMeetingDetailEmail'");
    // }


    @GetMapping("/meetingDetails")
    public ResponseEntity<List<MeetingDetails>> getAllMeetingDetails(){
        return meetingDetailsServiceImpl.getAllMeetingDetails();
    }

    @GetMapping("{Id}")
    public MeetingDetails getMeetingDetails(@PathVariable("Id") Long id){
        return meetingDetailsServiceImpl.getMeetingDetails(id);
    }

    @PutMapping()
    public ResponseEntity<MeetingDetails> updateMeetingDetails(@RequestBody MeetingDetails meetingDetails){
        return meetingDetailsServiceImpl.updateMeetingDetails(meetingDetails);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteMeetingDetails(@PathVariable Long id){
        return meetingDetailsServiceImpl.deleteMeetingDetails(id);
    }
}
