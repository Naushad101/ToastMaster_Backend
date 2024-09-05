package com.dev.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.dto.MemberDTO;
import com.dev.entity.MemberDetails;
import com.dev.entity.MemberShip;
import com.dev.exception.InvalidEmailAddressException;
import com.dev.exception.InvalidPhoneNumberException;
import com.dev.exception.MemberDetailsNotFoundException;
import com.dev.repository.MemberDetailsRepository;
import com.dev.repository.MembershipRepository;
import com.dev.service.MemberDetailsService;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MemberDetailsServiceImpl implements MemberDetailsService {

    @Autowired
    MemberDetailsRepository memberDetailsRepository;

    @Autowired
    MembershipRepository membershipRepository;

    @Autowired
	JavaMailSender sender;


    public void sendRegistrationEmail(String to, String firstName) throws MessagingException {
        log.info("service method calling");
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        log.info("Seeting to");
		simpleMailMessage.setTo(to);

        String subject = "Welcome to Toastmasters!";
        log.info("Setting subject");
        simpleMailMessage.setSubject(subject);

        String body = "Dear " + firstName + ",\n\n" +
                      "Thank you for registering on the Toastmasters website. We're excited to have you as a part of our community.\n" +
                      "You can now access your account and start exploring the various resources we offer.\n\n" +
                      "If you have any questions, feel free to reach out to us.\n\n" +
                      "Best regards,\n" +
                      "The Toastmasters Team";

        // Create a MimeMessage
        log.info("Creatingg MimeMEssae");
        MimeMessage mimeMessage = sender.createMimeMessage();

        // Use MimeMessageHelper to set the properties of the email
        MimeMessageHelper mimeHelper = new MimeMessageHelper(mimeMessage, false);
        mimeHelper.setTo(to);
        mimeHelper.setSubject(subject);
        mimeHelper.setText(body);

        log.info("sending mail");
        // Send the email
        sender.send(mimeMessage);
        log.info("mail sended");

    }



    @Override
    public ResponseEntity<MemberDTO> saveMemberDetails(MemberDTO memberDTO) {

        // if(memberDTO!=null){
        //     if(!(phoneNumberValid(memberDTO.getPhoneNumber()))){
        //         throw new InvalidPhoneNumberException("Invalid Phone Number");
        //     }

        //     if(!(emailValidate(memberDetails.getEmailAddress()))){
        //         throw new InvalidEmailAddressException("Invalid Email Address");
        //     }
        // }

        MemberDetails memberDetails = new MemberDetails();
        memberDetails.setFirstName(memberDTO.getFirstName());
        memberDetails.setLastName(memberDTO.getLastName());
        memberDetails.setAddress(memberDTO.getAddress());
        memberDetails.setDateOfBirth(memberDTO.getDateOfBirth());
        memberDetails.setPhoneNumber(memberDTO.getPhoneNumber());
        memberDetails.setEmailAddress(memberDTO.getEmailAddress());
        memberDetails.setDateTime(memberDTO.getDateTime());
        memberDetails.setProfession(memberDTO.getProfession());
        
       MemberDetails memberDetails2 = memberDetailsRepository.save(memberDetails);

        MemberShip memberShip = new MemberShip();

        memberShip.setFees(memberDTO.getFees());
        memberShip.setStartDate(memberDTO.getStartDate());
        memberShip.setEndDate(memberDTO.getEndDate());
        memberShip.setIsActive(memberDTO.getIsActive());
        memberShip.setMemberDetails(memberDetails);

        membershipRepository.save(memberShip);
        memberDTO.setId(memberDetails.getId());
       return new ResponseEntity<>(memberDTO,HttpStatus.CREATED);
    }

    @Override
    public List<MemberDetails> getAllMemberDetails() {
        return memberDetailsRepository.findAll();
    }

    @Override
    public ResponseEntity<Void> deleteMemberById(Long id) {
        if(memberDetailsRepository.findById(id).isEmpty()){
            throw new MemberDetailsNotFoundException("Memebr details with id " +id+ " is not present in database");
        }
        memberDetailsRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }

    public boolean emailValidate(String email){
        final String EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

        if(email==null || email.isEmpty()) return false;

        Pattern pattern = Pattern.compile(EMAIL_REGEX);

        Matcher matcher = pattern.matcher(email);

        return matcher.matches();

    }

    public boolean phoneNumberValid(String phoneNumber){
        if(phoneNumber.length()!=10){
            return false;
        }

        for(int i=0;i<phoneNumber.length();i++){
            if(!(phoneNumber.charAt(i)>='0' && phoneNumber.charAt(i)<='9')){
                return false;
            }
        }
        return true;
    }

    @Override
    public ResponseEntity<MemberDetails> updateMemberDetails(MemberDetails memberDetails) {
        Long id = memberDetails.getId();

        Optional<MemberDetails> memberDetails2 = memberDetailsRepository.findById(id);

        if(memberDetails2.isEmpty()){
            throw new MemberDetailsNotFoundException("MemberDetails with id "+id+" is not present in database");
        }

        MemberDetails existingMemberDetails = memberDetails2.get();

        if(memberDetails.getFirstName()!=null){
            existingMemberDetails.setFirstName(memberDetails.getFirstName());
        }
        if(memberDetails.getLastName()!=null){
            existingMemberDetails.setLastName(memberDetails.getLastName());
        }
        if(memberDetails.getAddress()!=null){
            existingMemberDetails.setAddress(memberDetails.getAddress());
        }
        if(memberDetails.getDateOfBirth()!=null){
            existingMemberDetails.setDateOfBirth(memberDetails.getDateOfBirth());
        }
        if(memberDetails.getEmailAddress()!=null){
            existingMemberDetails.setEmailAddress(memberDetails.getEmailAddress());
        }
        if(memberDetails.getPhoneNumber()!=null){
            existingMemberDetails.setPhoneNumber(memberDetails.getPhoneNumber());
        }
        if(memberDetails.getProfession()!=null){
            existingMemberDetails.setProfession(memberDetails.getProfession());
        }

        return new ResponseEntity<>(memberDetailsRepository.save(existingMemberDetails),HttpStatus.CREATED);
    }
    
}
