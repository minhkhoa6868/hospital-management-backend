package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.PatientService;

import main.java.com.dto.PatientDTO;

@RestController
@RequestMapping("/patient")
public class PatientController{
    private final PatientService patientServ;
    
    public PatientController(PatientService patientServ){
        this.patientServ = patientServ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientDTO>> getAllPatient(){
        List<PatientDTO> patients = patientServ.handleGetAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{code}")
    public ResponseEntity<PatientDTO> getPatient(@PatchVariable long code){
        PatientDTO patientDTO = patientServ.getOnePatient(code);
        return ResponseEntity.ok(patientDTO);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody PatientDTO patientDTO){
        System.out.println(patientDTO.getFirstname());
        Patient newPatient = patientServ.createPatient(patientDTO);
        patientDTO newPatientDTO = new patientDTO(newPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatientDTO);
    }

    @PutMapping("/{code}")
    public ResponseEntity<PatientPTO> updatePatient(@PatchVariable long code,@RequestBody patientDTO patientDTO){
        PatientDTO updatedDTO = patientServ.updatePatient(code,patientDTO);
        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllPatient(){
        patientServ.deleteAllPatient();
        return ResponseEntity.ok("All patients deleted successfully");
    }

    @DeleteMapping("/code")
    public ResponseEntity<String> deletePatient(@PatchVariable long code){
        patientServ.deletePatient(code);
        return ResponseEntity.ok("Patient deleted successfully");
    }
}