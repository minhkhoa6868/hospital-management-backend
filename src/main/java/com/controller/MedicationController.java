package com.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.MedicationDTO;
import com.service.MedicationService;

@RestController
@RequestMapping("/Medication")
public class MedicationController{
    private final MedicationService MedicationServ;
    
    public MedicationController(MedicationService MedicationServ){
        this.MedicationServ = MedicationServ;
    }

    @GetMapping("/all")
    public ResponseEntity<List<MedicationDTO>> getAllMedication(){
        List<MedicationDTO> Medications = MedicationServ.handleGetAllMedications();
        return ResponseEntity.ok(Medications);
    }

    @GetMapping("/{code}")
    public ResponseEntity<MedicationDTO> getMedication(@PatchVariable long code){
        MedicationDTO MedicationDTO = MedicationServ.getOneMedication(code);
        return ResponseEntity.ok(MedicationDTO);
    }

    @PostMapping
    public ResponseEntity<MedicationDTO> createMedication(@RequestBody MedicationDTO MedicationDTO){
        System.out.println(MedicationDTO.getName());
        Medication newMedication = MedicationServ.createMedication(MedicationDTO);
        MedicationDTO newMedicationDTO = new MedicationDTO(newMedication);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMedicationDTO);
    }

    @PutMapping("/{code}")
    public ResponseEntity<MedicationPTO> updateMedication(@PatchVariable long code,@RequestBody MedicationDTO MedicationDTO){
        MedicationDTO updatedDTO = MedicationServ.updateMedication(code,MedicationDTO);
        return ResponseEntity.ok(updatedDTO);
    }

    @DeleteMapping("/all")
    public ResponseEntity<String> deleteAllMedication(){
        MedicationServ.deleteAllMedication();
        return ResponseEntity.ok("All Medications deleted successfully");
    }

    @DeleteMapping("/code")
    public ResponseEntity<String> deleteMedication(@PatchVariable long code){
        MedicationServ.deleteMedication(code);
        return ResponseEntity.ok("Medication deleted successfully");
    }
}