package com.dev.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dev.entity.MeetingDetails;

public interface MeetingDetailsService {
    public ResponseEntity<MeetingDetails> saveMeetingDetails(MeetingDetails meetingDetails);

    public ResponseEntity<List<MeetingDetails>> getAllMeetingDetails();

    public ResponseEntity<MeetingDetails> updateMeetingDetails(MeetingDetails meetingDetails);

    public ResponseEntity<Void> deleteMeetingDetails(Long id);

}
