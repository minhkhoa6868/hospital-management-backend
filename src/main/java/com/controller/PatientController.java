package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.service.PatientService;
import com.service.TreatmentService;
import com.dto.PatientDTO;
import com.dto.TreatmentDTO;
import com.model.Patients;

@RestController
@RequestMapping("/patient")
@CrossOrigin(origins = "http://localhost:3000")
public class PatientController{
    private final PatientService patientServ;
    private final TreatmentService treatmentService;
    
    public PatientController(PatientService patientServ, TreatmentService treatmentService){
        this.patientServ = patientServ;
        this.treatmentService = treatmentService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<PatientDTO>> getAllPatient(){
        List<PatientDTO> patients = patientServ.handleGetAllPatient();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{code}")
    public ResponseEntity<PatientDTO> getPatient(@PathVariable String code){
        PatientDTO patientDTO = patientServ.handleGetOnePatient(code);
        return ResponseEntity.ok(patientDTO);
    }

    @PostMapping
    public ResponseEntity<PatientDTO> createPatient(@RequestBody Patients patient){
        Patients newPatient = patientServ.handleCreatePatient(patient);
        PatientDTO newPatientDTO = new PatientDTO(newPatient);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPatientDTO);
    }

    @GetMapping("/{code}/treatment")
    public ResponseEntity<List<TreatmentDTO>> getAllTreatmentOfPatient(@PathVariable String code) {
        List<TreatmentDTO> treatments = treatmentService.handleGetAllTreatmentOfPatient(code);

        return ResponseEntity.ok(treatments);
    }
    
}