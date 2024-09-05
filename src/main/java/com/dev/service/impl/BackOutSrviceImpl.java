package com.dev.service.impl;
import java.util.List;
import java.util.ArrayList;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.BackOut;
import com.dev.entity.MeetingDetails;
import com.dev.entity.RolesTaken;
import com.dev.exception.BackOutNotFoundException;
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
        rolesTaken.setMemberDetails(null);
        rolesTakenRepository.save(rolesTaken);

       BackOut backOut2 = backOutRepository.save(backOut);

       return new ResponseEntity<>(backOut2,HttpStatus.CREATED);

        
    }

    @Override
    public ResponseEntity<List<BackOut>> getAllBackOutList() {
        List<BackOut> backOuts = backOutRepository.findAll();
        List<BackOut> backOutList  = new ArrayList<>();
        for(BackOut backOut : backOuts){
            MeetingDetails meetingDetails = new MeetingDetails();
            MeetingDetails meetingDetails2 = backOut.getMeetingDetails();
            meetingDetails.setId(meetingDetails2.getId());
            meetingDetails.setTheme(meetingDetails2.getTheme());
            meetingDetails.setVenue(meetingDetails2.getVenue());
            meetingDetails.setDateTime(meetingDetails2.getDateTime());
            BackOut backOut2 = new BackOut();
            backOut2.setId(backOut.getId());
            backOut2.setBackOutReason(backOut.getBackOutReason());
            backOut2.setDateTime(backOut.getDateTime());
            backOut2.setMeetingDetails(meetingDetails);
            backOut2.setMemberDetails(backOut.getMemberDetails());
            backOut2.setRoleName(backOut.getRoleName());
            backOutList.add(backOut2);
        }
        if(backOuts==null){
            throw new BackOutNotFoundException("Back Out list is not present in database");
        }

        return new ResponseEntity<>(backOutList,HttpStatus.ACCEPTED);
    }
    
}
