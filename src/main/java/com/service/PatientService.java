package com.service;

import com.model.Patients;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.PatientDTO;
import com.repository.PatientRepository;

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
    public Patients findPatient(long code){
        Optional<Patients> targetPatient = this.patient_repo.findPCode(code);

        if (targetPatient.isPresent()){
            return targetPatient.get();
        }
        return null;
    }

    @Transactional
    public List<PatientDTO> handleGetAllPatient(){
        List<Patients> patients = patient_repo.findAll();

        return patients.stream().map(PatientDTO::new).toList();
    }

    public PatientDTO handleGetOnePatient(long code){
        Patients patient = this.findPatient(code);

        if (patient == null){
            return null;
        }
        return new PatientDTO(patient);
    }

    //create new patient
    @Transactional
    public Patients handleCreatePatient(PatientDTO patientDTO){
        System.out.println(patientDTO);

        Patients newPatient = new Patients();

        this.patient_repo.disableForeignKeyChecks();

        newPatient.setFirst_name(patientDTO.getFirstName());
        newPatient.setLast_name(patientDTO.getLastName());
        newPatient.setAddress(patientDTO.getAddress());
        newPatient.setPatient_type(patientDTO.getPatient_type());
        
        this.patient_repo.enableForeignKeyChecks();
        return patient_repo.save(newPatient);
    }
}