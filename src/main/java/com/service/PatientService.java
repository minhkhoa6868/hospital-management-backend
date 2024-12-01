package com.service;

import com.model.Patients;
import com.model.Patients.PatientType;

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
    public Patients findPatient(String code){
        Optional<Patients> targetPatient = this.patient_repo.findPCode(code);

        if (targetPatient.isPresent()){
            return targetPatient.get();
        }
        return null;
    }

    public List<PatientDTO> handleGetAllPatient(){
        List<Patients> patients = patient_repo.findAll();

        return patients.stream().map(PatientDTO::new).toList();
    }

    public PatientDTO handleGetOnePatient(String code){
        Patients patient = this.findPatient(code);

        if (patient == null){
            return null;
        }
        return new PatientDTO(patient);
    }

    //create new patient
    public Patients handleCreatePatient(Patients patient){
        Patients newPatient = patient_repo.save(patient);

        String patientCode = "";
        PatientType patientType = newPatient.getPatientType();
        Long id = newPatient.getId();

        if (patientType == PatientType.Inpatient){
            patientCode = "IP" + String.format("%09d", id);
        }

        else if (patientType == PatientType.Outpatient){
            patientCode = "OP" + String.format("%09d", id);
        }

        newPatient.setPcode(patientCode);

        return patient_repo.save(newPatient);
    }
}