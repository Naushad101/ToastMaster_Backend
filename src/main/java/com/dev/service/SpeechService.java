package com.dev.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.dev.entity.Speech;

public interface SpeechService {
    public ResponseEntity<Speech> saveSpeech(Speech speech);

    public ResponseEntity<List<Speech>> getAllSpeech();

    public ResponseEntity<Speech> updateSpeech(Speech speech);

    public ResponseEntity<Void> deleteSpeech(Long id);
}
