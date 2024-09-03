package com.dev.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.dev.entity.Availability;

public interface AvailabilityService {

    public ResponseEntity<Availability> saveAvailability(@RequestBody Availability availability);

    public ResponseEntity<List<Availability>> getAllAvailability();
}
