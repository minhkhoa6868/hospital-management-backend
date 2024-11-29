package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.PatientService;

import com.dto.PatientDTO;
import com.model.Patients;

@RestController
@RequestMapping("/patient")
public class PatientController{
    private final PatientService patientServ;
    
    public PatientController(PatientService patientServ){
        this.patientServ = patientServ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientDTO>> getAllPatient(){
        List<PatientDTO> patients = patientServ.handleGetAllPatient();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{code}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable long code){
        PatientDTO patientDTO = patientServ.handleGetOnePatient(code);
        return ResponseEntity.ok(patientDTO);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO){
        Patients newPatient = patientServ.handleCreatePatient(patientDTO);
        PatientDTO newPatientDTO = new PatientDTO(newPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatientDTO);
    }
}