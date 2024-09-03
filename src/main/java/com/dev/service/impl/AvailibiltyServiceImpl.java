package com.dev.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.Availability;
import com.dev.entity.MeetingDetails;
import com.dev.entity.MemberDetails;
import com.dev.exception.AvailabilityNotFoundException;
import com.dev.exception.MeetingDetailsNotFoundException;
import com.dev.exception.MemberDetailsNotFoundException;
import com.dev.repository.AvailabilityRespository;
import com.dev.repository.MeetingDetailsRepository;
import com.dev.repository.MemberDetailsRepository;
import com.dev.service.AvailabilityService;

@Service
public class AvailibiltyServiceImpl implements AvailabilityService {

    @Autowired
    MeetingDetailsRepository meetingDetailsRepository;

    @Autowired
    MemberDetailsRepository memberDetailsRepository;

    @Autowired
    AvailabilityRespository availabilityRespository;

    @Override
    public ResponseEntity<Availability> saveAvailability(Availability availability) {

        Long meetingId = availability.getMeeting().getId();


        Optional<MeetingDetails> meOptional = meetingDetailsRepository.findById(meetingId);
        MeetingDetails meetingDetails = new MeetingDetails();
        meetingDetails.setId(meOptional.get().getId());
        meetingDetails.setTheme(meOptional.get().getTheme());
        meetingDetails.setVenue(meOptional.get().getVenue());
        meetingDetails.setDateTime(meOptional.get().getDateTime());


        if(meOptional.isEmpty()){
            throw new MeetingDetailsNotFoundException("Meeting Details with id "+meetingId+" is not present in database");
        }

        Long memberId = availability.getMember().getId();

        Optional<MemberDetails> memberOptional = memberDetailsRepository.findById(memberId);

        if(memberOptional.isEmpty()){
            throw new MemberDetailsNotFoundException("Member Details with id "+memberId+" is not present in database");
        }

        availability.setMeeting(meetingDetails);
        availability.setMember(memberOptional.get());

        return new ResponseEntity<>(availabilityRespository.save(availability),HttpStatus.CREATED);


    }

    @Override
    public ResponseEntity<List<Availability>> getAllAvailability() {
        List<Availability> availabilities = availabilityRespository.findAll();
        if(availabilities==null){
            throw new AvailabilityNotFoundException("Availabilities not found in database");
        }
        return new ResponseEntity<>(availabilities,HttpStatus.ACCEPTED);
    }
    
}
