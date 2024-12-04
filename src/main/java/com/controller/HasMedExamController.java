package com.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.HasMedExamDTO;
import com.model.HasMedExam.HasMedExam;
import com.service.HasMedExamService;

@RestController
@RequestMapping("/hasMedExam")
@CrossOrigin(origins = "http://localhost:3000")
public class HasMedExamController {
    private final HasMedExamService hasMedExamService;

    public HasMedExamController(HasMedExamService hasMedExamService) {
        this.hasMedExamService = hasMedExamService;
    }

    // create HasMedExam
    @PostMapping
    public ResponseEntity<HasMedExamDTO> creatHasMedExam(@RequestBody HasMedExam hasMedExam) {
        HasMedExamDTO newHasMedExam = this.hasMedExamService.handleCreateHasMedExam(hasMedExam);

        return ResponseEntity.status(HttpStatus.CREATED).body(newHasMedExam);
    }
}
