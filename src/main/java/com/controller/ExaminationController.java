package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.ExaminationDTO;
import com.dto.MedicationDTO;
import com.model.Examination;
import com.service.ExaminationService;
import com.service.HasMedExamService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/examination")
public class ExaminationController {
    private final ExaminationService examinationService;
    private final HasMedExamService hasMedExamService;

    public ExaminationController(ExaminationService examinationService, HasMedExamService hasMedExamService) {
        this.examinationService = examinationService;
        this.hasMedExamService = hasMedExamService;
    }

    @PostMapping
    public ResponseEntity<ExaminationDTO> createExamination(@RequestBody Examination examination) {
        ExaminationDTO newExamination = this.examinationService.handleCreateExamination(examination);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newExamination);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ExaminationDTO>> getAllExamination() {
        List<ExaminationDTO> examinations = this.examinationService.handleGetAllExamination();

        return ResponseEntity.ok(examinations);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<ExaminationDTO> getOneExamination(@PathVariable long id) {
        ExaminationDTO examination = this.examinationService.handleGetOneExamination(id);

        return ResponseEntity.ok(examination);
    }

    // get all medication of examination
    @GetMapping("/{id}/medication")
    public ResponseEntity<List<MedicationDTO>> getAllMedication(@PathVariable long id) {
        List<MedicationDTO> medications = this.hasMedExamService.getAllMedicationByExamId(id);

        return ResponseEntity.ok(medications);
    }
}
