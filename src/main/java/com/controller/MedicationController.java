package com.controller;

import com.model.Medication;

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

import com.dto.MedicationDTO;
import com.service.MedicationService;

@RestController
@RequestMapping("/medication")
@CrossOrigin(origins = "http://localhost:5173")
public class MedicationController{
    private final MedicationService MedicationServ;
    
    public MedicationController(MedicationService MedicationServ){
        this.MedicationServ = MedicationServ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicationDTO>> getAllMedication(){
        List<MedicationDTO> Medications = MedicationServ.handleGetAllMedication();
        return ResponseEntity.ok(Medications);
    }

    @GetMapping("/{code}")
    public ResponseEntity<MedicationDTO> getMedication(@PathVariable long code){
        MedicationDTO MedicationDTO = MedicationServ.getOneMedication(code);
        return ResponseEntity.ok(MedicationDTO);
    }

    @PostMapping
    public ResponseEntity<MedicationDTO> createMedication(@RequestBody MedicationDTO MedicationDTO){
        Medication newMedication = MedicationServ.createMedication(MedicationDTO);
        MedicationDTO newMedicationDTO = new MedicationDTO(newMedication);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMedicationDTO);
    }
}