package com.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping("api/meeting")
public class MeetingDetailsController {

    @Autowired
    MeetingDetailsServiceImpl meetingDetailsServiceImpl;

    @PostMapping()
    public ResponseEntity<MeetingDetails> saveMeetingDetails(@RequestBody MeetingDetails meetingDetails){
        return meetingDetailsServiceImpl.saveMeetingDetails(meetingDetails);
    }

    @GetMapping()
    public ResponseEntity<List<MeetingDetails>> getAllMeetingDetails(){
        return meetingDetailsServiceImpl.getAllMeetingDetails();
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
