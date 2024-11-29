package com.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.model.Patient;
import com.repository.PatientRepository;

import main.java.com.dto.PatientDTO;

@Service
@Transactional
public class PatientService{
    private final PatientRepository patient_repo;

    //initialize
    public PatientService(PatientRepository patient_repo){
        this.patient_repo = patient_repo;
    }

    //find by PCode
    public Patient findPatient(long pcode){
        Optional<Patient> patient = this.patient_repo.findPCode(pcode);
        if (patient.isPresent()){
            return patient.get();
        } else {
            return null;
        }
    }

    public List<Patient> getAllPatient(Patient patient){
        List<Patient> patients = patient_repo.findAll();

        return patients.stream().map(patient -> new PatientDTO(patient)).collect(Collectors.toList());
    }

}