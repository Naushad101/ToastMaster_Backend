package com.dev.service;
import java.util.*;

import org.springframework.http.ResponseEntity;

import com.dev.entity.BackOut;

public interface BackOutService {
    
    public ResponseEntity<BackOut> backOutRequest(BackOut backOut);

    public ResponseEntity<List<BackOut>> getAllBackOutList();
}
