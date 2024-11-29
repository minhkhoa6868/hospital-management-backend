package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    @Transactional
    public Patient findPatient(long code){
        Optional<Patient> targetPatient = this.patient_repo.findPCode(code);

        if (targetPatient.isPresent()){
            return targetPatient.get();
        }
        return null;
    }

    @Transactional
    public List<Patient> getAllPatient(Patient patient){
        List<Patient> patients = patient_repo.findAll();

        return patients.stream().map(PatientDTO::new).toList();
    }

    public PatientDTO getOnePatient(long code){
        Patient patient = this.findPatient(code);

        if (patient == null){
            throw new RuntumeException("Patient not found");
        }
        return new PatientDTO(patient);
    }

    //create new patient
    @Transactional
    public Patient createPatient(PatientDTO patientDTO){
        System.out.println(patientDTO);

        Patient newPatient = new Patient();

        this.patient_repo.disableForeignKeyChecks();

        newPatient.setFirstname(patientDTO.getFirstname());
        newPatient.setLastname(patientDTO.getLastname());
        newPatient.setdob(patientDTO.getdob());
        newPatient.setGender(patientDTO.getGender());
        newPatient.setAdress(patientDTO.getAdress());
        newPatient.setPatienttype(patientDTO.getPatienttype());

        this.patient_repo.enableForeignKeyChecks();
        return patient_repo.save(newPatient);
    }

    //update patient info
    @Transactional
    public PatientDTO updatePatient(long code, PatientDTO patientDTO){
        Patient updated = this.findPatient(code);

        if (updated==null){
            throw new RuntimeException("Patient not found");
        }

        updated.setFirstname(patientDTO.getFirstname());
        updated.setLastname(patientDTO.getLastname());
        updated.setdob(patientDTO.getdob());
        updated.setGender(patientDTO.getGender());
        updated.setAdress(patientDTO.getAdress());
        updated.setPatienttype(patientDTO.getPatienttype());
        Patient saved = patient_repo.save(updated);
        return new PatientDTO(saved);
    }

    //clear patient list
    @Transactional
    public void deleteAllPatients() {
        patient_repo.deleteAll();
    }
    
    //delete patient
    @Transactional
    public void deletePatient(long code){
        Patient patient = this.findPatient(code);
        if (patient == null) {throw new RuntimeException("Patient not found");}
        patient_repo.delete(patient);
    }
}