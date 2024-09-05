package com.dev.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.Availability;
import com.dev.entity.MeetingDetails;
import com.dev.entity.MemberDetails;
import com.dev.entity.PreferredRoles;
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

        Availability availability2 = new Availability();

        availability2.setId(availability.getId());
        availability2.setIsAvailable(availability.getIsAvailable());
        availability2.setMeeting(meetingDetails);
        availability2.setMember(memberOptional.get());

        List<PreferredRoles> preferredRoles = availability.getPreferredRoles();

        Availability availability3 = availabilityRespository.save(availability);

        for(PreferredRoles preferredRoles2 : preferredRoles){
            
        }

        return new ResponseEntity<>(availability3,HttpStatus.CREATED);


    }

    @Override
    public ResponseEntity<List<Availability>> getAllAvailability() {
        List<Availability> availabilities = availabilityRespository.findAll();
        if(availabilities==null){
            throw new AvailabilityNotFoundException("Availabilities not found in database");
        }

        List<Availability> availabilitiesList = new ArrayList<>();

        for(Availability availability : availabilities){
            MeetingDetails meetingDetails = new MeetingDetails();
            MeetingDetails meetingDetails2 = availability.getMeeting();
            meetingDetails.setId(meetingDetails2.getId());
            meetingDetails.setTheme(meetingDetails2.getTheme());
            meetingDetails.setVenue(meetingDetails2.getVenue());
            meetingDetails.setDateTime(meetingDetails2.getDateTime());
          availability.setMeeting(meetingDetails);
          availabilitiesList.add(availability);
        }

        return new ResponseEntity<>(availabilitiesList,HttpStatus.ACCEPTED);
    }
    
}
