package com.dev.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MemberDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(name="first_name")
    String firstName;

    @Column(name="last_name")
    String lastName;

    @Column(name="address")
    String address;

    @Column(name="phone_number")
    String phoneNumber;

    @Column(name="email_address")
    String emailAddress;

    @Column(name="profession")
    String profession;

    @Column(name="date_of_birth")
    String dateOfBirth;

    @Column(name="club_joining_date")
    private LocalDateTime dateTime;
}
