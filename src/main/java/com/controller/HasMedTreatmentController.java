package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.HasMedTreatmentDTO;
import com.model.HasMedTreatment.HasMedTreatment;
import com.service.HasMedTreatmentService;

@RestController
@RequestMapping("/hasMedTreatment")
@CrossOrigin(origins = "http://localhost:3000")
public class HasMedTreatmentController {
    private final HasMedTreatmentService hasMedTreatmentService;

    public HasMedTreatmentController(HasMedTreatmentService hasMedTreatmentService) {
        this.hasMedTreatmentService = hasMedTreatmentService;
    }

    // create HasMedTreatment
    @PostMapping
    public ResponseEntity<HasMedTreatmentDTO> createHasMedTreatment(@RequestBody HasMedTreatment hasMedTreatment) {
        HasMedTreatmentDTO newHasMedTreatment = hasMedTreatmentService.handleCreateHasMedTreatment(hasMedTreatment);
        return ResponseEntity.status(HttpStatus.CREATED).body(newHasMedTreatment);
    }
}
