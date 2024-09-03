package com.dev.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.MeetingDetails;
import com.dev.entity.MemberDetails;
import com.dev.entity.Speech;
import com.dev.exception.MeetingDetailsNotFoundException;
import com.dev.exception.MemberDetailsNotFoundException;
import com.dev.exception.SpeechNotFoundException;
import com.dev.repository.MeetingDetailsRepository;
import com.dev.repository.MemberDetailsRepository;
import com.dev.repository.SpeechRepository;
import com.dev.service.SpeechService;

@Service
public class SpeechServiceImpl implements SpeechService {

    @Autowired
    SpeechRepository speechRepository;

    @Autowired
    MemberDetailsRepository memberDetailsRepository;

    @Autowired
    MeetingDetailsRepository meetingDetailsRepository;

    @Override
    public ResponseEntity<Speech> saveSpeech(Speech speech) {

        Optional<MemberDetails> eMemberDetails = memberDetailsRepository.findById(speech.getMemberDetails().getId());

        Optional<MeetingDetails> meetingDetaOptional = meetingDetailsRepository.findById(speech.getMeetingDetails().getId());

        if(eMemberDetails.isEmpty()){
            throw new MemberDetailsNotFoundException("Member details with id "+speech.getMemberDetails().getId()+" is not present in database");
        }

        if(meetingDetaOptional.isEmpty()){
            throw new  MeetingDetailsNotFoundException("Meeting details with id "+speech.getMemberDetails().getId()+" is not present in database");
        }
        speech.setMemberDetails(eMemberDetails.get());
        speech.setMeetingDetails(meetingDetaOptional.get());
        return new ResponseEntity<>(speechRepository.save(speech),HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<List<Speech>> getAllSpeech() {
        List<Speech> speechs = speechRepository.findAll();
        if(speechs==null){
            throw new SpeechNotFoundException("Speeches are not present in database");
        }

        return new ResponseEntity<>(speechRepository.findAll(),HttpStatus.FOUND);

    }

    @Override
    public ResponseEntity<Speech> updateSpeech(Speech speech) {
        
        Long id = speech.getId();
        Optional<Speech> speOptional = speechRepository.findById(id);

        if(speOptional.isEmpty()){
            throw new SpeechNotFoundException("Speech with id "+id+" is not present in database");
        }

        Long memberId = speech.getMemberDetails().getId();

        Optional<MemberDetails> memOptional = memberDetailsRepository.findById(memberId);

        if(memOptional.isEmpty()){
            throw new MemberDetailsNotFoundException("Member Details with id "+memberId+" is not present in database");
        }

        Long meetingId = speech.getMeetingDetails().getId();
        Optional<MeetingDetails> meeOptional = meetingDetailsRepository.findById(meetingId);

        if(meeOptional.isEmpty()){
            throw new MeetingDetailsNotFoundException("MeetingDetails with id "+meetingId+" is not present in database");
        }

        Speech existingSpeech = speOptional.get();

        existingSpeech.setMemberDetails(memOptional.get());
        existingSpeech.setMeetingDetails(meeOptional.get());

        if(speech.getTitle()!=null){
            existingSpeech.setTitle(speech.getTitle());
        }

        if(speech.getDescription()!=null){
            existingSpeech.setDescription(speech.getDescription());
        }

        return new ResponseEntity<>(speechRepository.save(existingSpeech),HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Void> deleteSpeech(Long id) {
        if(speechRepository.findById(id).isEmpty()){
            throw new SpeechNotFoundException("Speech with id "+id+" is not present in database");
        }
        speechRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
    
}
