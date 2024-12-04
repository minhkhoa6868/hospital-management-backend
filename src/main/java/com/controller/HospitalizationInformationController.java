package com.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dto.HospitalizationInformationDTO;
import com.model.HospitalizationInformation;
import com.service.HospitalizationInformationService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/hospitalization_information")
@CrossOrigin(origins = "http://localhost:5173")
public class HospitalizationInformationController {
    private final HospitalizationInformationService hospitalizationInformationService;

    public HospitalizationInformationController(HospitalizationInformationService hospitalizationInformationService) {
        this.hospitalizationInformationService = hospitalizationInformationService;
    }

    // get all information
    @GetMapping("/all")
    public ResponseEntity<List<HospitalizationInformationDTO>> getAllInformation() {
        List<HospitalizationInformationDTO> hospitalizationInformationDTOs = this.hospitalizationInformationService.handleGetAllInformation();

        return ResponseEntity.ok(hospitalizationInformationDTOs);
    }

    // get one information
    @GetMapping("/{id}")
    public ResponseEntity<HospitalizationInformationDTO> getOneInformation(@PathVariable long id) {
        HospitalizationInformationDTO hospitalizationInformationDTO = this.hospitalizationInformationService.handleGetOneInformation(id);

        return ResponseEntity.ok(hospitalizationInformationDTO);
    }
    
    // create information
    @PostMapping
    public ResponseEntity<HospitalizationInformationDTO> createInformation(@RequestBody HospitalizationInformation hi) {
        HospitalizationInformationDTO newInformation = this.hospitalizationInformationService.handleCreateInformation(hi);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(newInformation);
    }
}
