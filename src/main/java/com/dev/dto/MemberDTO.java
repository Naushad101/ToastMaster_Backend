package com.dev.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.Getter;

import lombok.Setter;

@Setter
@Getter
public class MemberDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private String phoneNumber;
    private String emailAddress;
    private String profession;
    private String dateOfBirth;
    private LocalDate dateTime;

    // MemberShip fields
    private int fees;
    private LocalDate startDate;
    private LocalDate endDate;
    private Boolean isActive;
}
