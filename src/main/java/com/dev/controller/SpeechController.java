package com.dev.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.entity.Speech;
import com.dev.service.impl.SpeechServiceImpl;

@RestController
@RequestMapping("api/speech")
public class SpeechController {

    @Autowired
    SpeechServiceImpl speechServiceImpl;

    @PostMapping()
    public ResponseEntity<Speech> saveSpeech(@RequestBody Speech speech){
        return speechServiceImpl.saveSpeech(speech);
    }

    @GetMapping()
    public ResponseEntity<List<Speech>> getAllSpeech(){
        return speechServiceImpl.getAllSpeech();
    }

    @PutMapping()
    public ResponseEntity<Speech> updateSpeech(@RequestBody Speech speech){
        return speechServiceImpl.updateSpeech(speech);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSpeech(@PathVariable Long id){
           return speechServiceImpl.deleteSpeech(id);
    }

    
}
