package com.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.Availability;
import com.dev.service.impl.AvailibiltyServiceImpl;

@RestController
@RequestMapping("/api/availability")
public class AvailabilityController {

    @Autowired
    AvailibiltyServiceImpl availibiltyServiceImpl;

    @PostMapping()
    public ResponseEntity<Availability> saveAvailability(@RequestBody Availability availability){
        return availibiltyServiceImpl.saveAvailability(availability); 
    }

    @GetMapping()
    public ResponseEntity<List<Availability>> getAllAvailability(){
        return availibiltyServiceImpl.getAllAvailability();
    }
}
