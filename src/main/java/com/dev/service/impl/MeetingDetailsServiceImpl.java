package com.dev.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.MeetingDetails;
import com.dev.entity.RolesTaken;
// import com.dev.entity.Roles;
import com.dev.exception.MeetingDetailsNotFoundException;
import com.dev.exception.RoleNotFoundException;
import com.dev.repository.MeetingDetailsRepository;
import com.dev.repository.MemberDetailsRepository;
import com.dev.repository.RolesTakenRepository;
// import com.dev.repository.RolesRepository;
import com.dev.service.MeetingDetailsService;

@Service
public class MeetingDetailsServiceImpl implements MeetingDetailsService {

    // @Autowired
    // RolesRepository rolesRepository;

    @Autowired
    MeetingDetailsRepository meetingDetailsRepository;

    @Autowired
    RolesTakenRepository rolesTakenRepository;

    @Autowired
    MemberDetailsRepository memberDetailsRepository;

    @Override
    public ResponseEntity<MeetingDetails> saveMeetingDetails(MeetingDetails meetingDetails) {
        
        MeetingDetails meetingDetails2 = new MeetingDetails();
        meetingDetails2.setVenue(meetingDetails.getVenue());
        meetingDetails2.setDateTime(meetingDetails.getDateTime());
        meetingDetails2.setTheme(meetingDetails.getTheme());

        

        List<RolesTaken> rolesTakens = meetingDetails.getRoles();

        meetingDetails = meetingDetailsRepository.save(meetingDetails2);

        for(RolesTaken rolesTaken : rolesTakens){
            rolesTaken.setMemberDetails(memberDetailsRepository.findByFirstName(rolesTaken.getMemberDetails().getFirstName()).orElseThrow());
            rolesTaken.setMeetingDetails(meetingDetails2);
            rolesTakenRepository.save(rolesTaken);
        }

        return new ResponseEntity<>(meetingDetails,HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<MeetingDetails>> getAllMeetingDetails() {
       List<MeetingDetails> meetingDetails = meetingDetailsRepository.findAll();
       List<MeetingDetails> meetingDetailsList = new ArrayList<>();
       if(meetingDetails==null){
            throw new MeetingDetailsNotFoundException("Meeting details not present in database");
       }
       for(MeetingDetails meeting : meetingDetails){
            MeetingDetails mDetails = new MeetingDetails();
            mDetails.setId(meeting.getId());
            mDetails.setDateTime(meeting.getDateTime());
            mDetails.setTheme(meeting.getTheme());
            mDetails.setVenue(meeting.getVenue());
            meetingDetailsList.add(mDetails);
       }
       return new ResponseEntity<>(meetingDetailsList,HttpStatus.ACCEPTED);
    }

    @Override
    public ResponseEntity<MeetingDetails> updateMeetingDetails(MeetingDetails meetingDetails) {
        Long id = meetingDetails.getId();
        Optional<MeetingDetails> meetingDetails1 = meetingDetailsRepository.findById(id);
        if(meetingDetails1.isEmpty()){
            throw new MeetingDetailsNotFoundException("Meeting Details with id "+id+" is not present into database");
        }

        MeetingDetails existingMeetingDetails = meetingDetails1.get();

        existingMeetingDetails.setId(meetingDetails.getId());
        existingMeetingDetails.setDateTime(meetingDetails.getDateTime());
        existingMeetingDetails.setVenue(meetingDetails.getVenue());
        //existingMeetingDetails.setSpeech(meetingDetails.getSpeech());
        //existingMeetingDetails.setRoles(meetingDetails.getRoles());

        return new ResponseEntity<>(meetingDetailsRepository.save(existingMeetingDetails),HttpStatus.CREATED);


    }

    @Override
    public ResponseEntity<Void> deleteMeetingDetails(Long id) {
        if(meetingDetailsRepository.findById(id).isEmpty()){
            throw new MeetingDetailsNotFoundException("Meeting details with id "+id+" is not present in database");
        }

        meetingDetailsRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    @Override
    public MeetingDetails getMeetingDetails(Long id) {
        Optional<MeetingDetails> meeOptional = meetingDetailsRepository.findById(id);
        MeetingDetails mDetails = new MeetingDetails();
        mDetails.setId(id);
        mDetails.setDateTime(meeOptional.get().getDateTime());
        mDetails.setTheme(meeOptional.get().getTheme());
        mDetails.setVenue(meeOptional.get().getVenue());

         List<RolesTaken> rolesTakens = rolesTakenRepository.findByMeetingDetails(mDetails);
        List<RolesTaken> rolesTakenList = new ArrayList<>();
        for(RolesTaken rolesTaken : rolesTakens){
           rolesTaken.setMeetingDetails(null);
           rolesTakenList.add(rolesTaken);
        }
        mDetails.setRoles(rolesTakenList);
        return mDetails;
    }
    
}
