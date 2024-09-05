package com.dev.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false)
    private MemberDetails member;

    @ManyToOne
    @JoinColumn(name = "meeting_id", nullable = false)
    private MeetingDetails meeting;

    @JsonProperty("isAvailable")
    private boolean isAvailable;

    @OneToMany(mappedBy = "availability",cascade = CascadeType.ALL)
    private List<PreferredRoles> preferredRoles; 


    public void setIsAvailable(boolean isAvailable){
        this.isAvailable=isAvailable;
    }

    public boolean getIsAvailable(){
        return this.isAvailable;
    }

}
