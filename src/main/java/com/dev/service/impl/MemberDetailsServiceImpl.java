package com.dev.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.entity.MemberDetails;
import com.dev.exception.InvalidEmailAddressException;
import com.dev.exception.InvalidPhoneNumberException;
import com.dev.exception.MemberDetailsNotFoundException;
import com.dev.repository.MemberDetailsRepository;
import com.dev.service.MemberDetailsService;

@Service
public class MemberDetailsServiceImpl implements MemberDetailsService {

    @Autowired
    MemberDetailsRepository memberDetailsRepository;

    @Override
    public ResponseEntity<MemberDetails> saveMemberDetails(MemberDetails memberDetails) {

        if(memberDetails!=null){
            if(!(phoneNumberValid(memberDetails.getPhoneNumber()))){
                throw new InvalidPhoneNumberException("Invalid Phone Number");
            }

            if(!(emailValidate(memberDetails.getEmailAddress()))){
                throw new InvalidEmailAddressException("Invalid Email Address");
            }
        }

       return new ResponseEntity<>(memberDetailsRepository.save(memberDetails),HttpStatus.CREATED);
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
