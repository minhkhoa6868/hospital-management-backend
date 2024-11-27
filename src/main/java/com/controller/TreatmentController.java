package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.TreatmentDTO;
import com.model.Treatment;
import com.service.TreatmentService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/treatment")
public class TreatmentController {
    private final TreatmentService treatmentService;

    public TreatmentController(TreatmentService treatmentService) {
        this.treatmentService = treatmentService;
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
}
