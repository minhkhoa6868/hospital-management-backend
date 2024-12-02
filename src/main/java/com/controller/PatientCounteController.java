package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.model.PatientCounter;
import com.service.PatientCounterService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/patientCounter")
public class PatientCounteController {
    private final PatientCounterService patientCounterService;

    public PatientCounteController(PatientCounterService patientCounterService) {
        this.patientCounterService = patientCounterService;
    }

    // create
    @PostMapping
    public ResponseEntity<PatientCounter> createPatientCounter(@RequestBody PatientCounter patientCounter) {
        PatientCounter newPatientCounter = this.patientCounterService.handleCreatePatientCounter(patientCounter);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatientCounter);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientCounter>> getAllPatientCounter() {
        List<PatientCounter> patientCounters = this.patientCounterService.handleGetAllCounter();
        return ResponseEntity.ok(patientCounters);
    }
    
    
}
