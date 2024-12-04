package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.MedicationDTO;
import com.dto.TreatmentDTO;
import com.model.Treatment;
import com.service.HasMedTreatmentService;
import com.service.TreatmentService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/treatment")
@CrossOrigin(origins = "http://localhost:5173")
public class TreatmentController {
    private final TreatmentService treatmentService;
    private final HasMedTreatmentService hasMedTreatmentService;

    public TreatmentController(TreatmentService treatmentService, HasMedTreatmentService hasMedTreatmentService) {
        this.treatmentService = treatmentService;
        this.hasMedTreatmentService = hasMedTreatmentService;
    }

    // get all treatments
    @GetMapping("/all")
    public ResponseEntity<List<TreatmentDTO>> getAllTreatment() {
        List<TreatmentDTO> treatmentDTOs = treatmentService.handleGetAllTreatment();

        return ResponseEntity.ok(treatmentDTOs);
    }
    
    // get one treatment
    @GetMapping("/{id}")
    public ResponseEntity<TreatmentDTO> getOneTreatment(@PathVariable long id) {
        TreatmentDTO treatmentDTO = treatmentService.handleGetOneTreatment(id);

        return ResponseEntity.ok(treatmentDTO);
    }

    // create treatment
    @PostMapping
    public ResponseEntity<TreatmentDTO> createTreatment(@RequestBody Treatment treatment) {
        TreatmentDTO newTreatment = treatmentService.handleCreateTreatment(treatment);

        return ResponseEntity.status(HttpStatus.CREATED).body(newTreatment);
    }
    
    // get all medication of treatment
    @GetMapping("/{id}/medication")
    public ResponseEntity<List<MedicationDTO>> getAllMedicationOfTreatment(@PathVariable long id) {
        List<MedicationDTO> medications = this.hasMedTreatmentService.handleGetAllMedicationByTreatId(id);

        return ResponseEntity.ok(medications);
    }
}
