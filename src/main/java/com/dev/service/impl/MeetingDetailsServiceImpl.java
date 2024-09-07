package com.dev.service.impl;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
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

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;




@Slf4j
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

     @Autowired
	JavaMailSender sender;

   
    public void sendMeetingDetailEmail(String to, String theme, String venue, String dateTime) throws MessagingException {
        log.info("Service method calling");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        log.info("Setting recipient");
        simpleMailMessage.setTo(to);

        String subject = "Meeting Creation Request Received";
        log.info("Setting subject");
        simpleMailMessage.setSubject(subject);

        String body = "Hi Dear,\n\n" +
                      "Thank you for requesting a meeting creation with Toastmasters. We appreciate you reaching out to us.\n\n" +
                      "Here are the details of your request:\n" +
                      "Theme: " + theme + "\n" +
                      "Venue: " + venue + "\n" +
                      "Date and Time: " + dateTime + "\n\n" +
                      "Our team will review your request and get back to you shortly.\n\n" +
                      "If you have any questions or need further assistance, please feel free to contact us.\n\n" +
                      "Best regards,\n" +
                      "The Toastmasters Team";

        // Create a MimeMessage
        log.info("Creating MimeMessage");
        MimeMessage mimeMessage = sender.createMimeMessage();

        // Use MimeMessageHelper to set the properties of the email
        MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, false);
        mimeHelper.setTo(to);
        mimeHelper.setSubject(subject);
        mimeHelper.setText(body);

        log.info("Sending mail");
        // Send the email
        sender.send(mimeMessage);
        log.info("Mail sent");
    }


    @Override
    public ResponseEntity saveMeetingDetails(MeetingDetails meetingDetails) throws MessagingException {
        
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
            sendMeetingDetailEmail(rolesTaken.getMemberDetails().getEmailAddress(), meetingDetails2.getTheme(), meetingDetails2.getVenue(), meetingDetails2.getDateTime().toString());
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
