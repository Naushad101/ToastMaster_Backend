package com.dev.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.dev.entity.MeetingDetails;
import com.dev.entity.RolesTaken;

@Repository
public interface RolesTakenRepository extends JpaRepository<RolesTaken,Long> {

    public Optional<RolesTaken> findByRoleName(String roleName);

    List<RolesTaken> findByMeetingDetails(MeetingDetails meetingDetails);
}
