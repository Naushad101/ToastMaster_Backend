package com.dev.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class BackOut {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="role_name")
    private String roleName;

    @Column(name="back_out_reason")
    private String backOutReason;

    @Column(name="back_out_date")
    private LocalDateTime dateTime;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private MeetingDetails meetingDetails;

    @ManyToOne
    @JoinColumn(name="member_id")
    private MemberDetails memberDetails;
}
