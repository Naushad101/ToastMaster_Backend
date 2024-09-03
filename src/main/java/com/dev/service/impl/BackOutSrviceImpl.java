package com.dev.service.impl;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.BackOut;
import com.dev.entity.MeetingDetails;
import com.dev.entity.RolesTaken;
import com.dev.repository.BackOutRepository;
import com.dev.repository.RolesTakenRepository;
import com.dev.service.BackOutService;

@Service
public class BackOutSrviceImpl implements BackOutService {

    @Autowired
    RolesTakenServiceImpl rolesTakenServiceImpl;

    @Autowired
    BackOutRepository backOutRepository;

    @Autowired
    RolesTakenRepository rolesTakenRepository;

    @Override
    public ResponseEntity<BackOut> backOutRequest(BackOut backOut) {

        Optional<RolesTaken> roletakeOptional = rolesTakenRepository.findByRoleName(backOut.getRoleName());

        RolesTaken rolesTaken = roletakeOptional.get();

        MeetingDetails meetingDetails = new MeetingDetails();
        meetingDetails.setId(rolesTaken.getMeetingDetails().getId());
        meetingDetails.setTheme(rolesTaken.getMeetingDetails().getTheme());
        meetingDetails.setVenue(rolesTaken.getMeetingDetails().getVenue());
        meetingDetails.setDateTime(rolesTaken.getMeetingDetails().getDateTime());

        backOut.setMeetingDetails(meetingDetails);
        backOut.setMemberDetails(rolesTaken.getMemberDetails());

        rolesTaken.setAvailableRole(true);
        rolesTaken.setRoleName(null);
        rolesTaken.setMemberDetails(null);
        rolesTakenRepository.save(rolesTaken);

       BackOut backOut2 = backOutRepository.save(backOut);

       return new ResponseEntity<>(backOut2,HttpStatus.CREATED);

        
    }
    
}
