package com.dev.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Speech {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="member_details_id")
    MemberDetails memberDetails;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="meeting_details_id")
    private MeetingDetails meetingDetails;
}
