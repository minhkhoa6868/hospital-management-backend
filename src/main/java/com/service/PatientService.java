package com.service;

import com.model.PatientCounter;
import com.model.Patients;
import com.model.Patients.PatientType;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.PatientDTO;
import com.exception.NotFoundException;
import com.repository.PatientCounterRepository;
import com.repository.PatientRepository;

@Service
@Transactional
public class PatientService{
    private final PatientRepository patient_repo;
    private final PatientCounterRepository patientCounterRepository;
    private final PatientCounterService patientCounterService;

    //initialize
    public PatientService(PatientRepository patient_repo, PatientCounterService patientCounterService, PatientCounterRepository patientCounterRepository){
        this.patient_repo = patient_repo;
        this.patientCounterService = patientCounterService;
        this.patientCounterRepository = patientCounterRepository;
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
    public Patients handleCreatePatient(PatientDTO patientDTO){
        Patients newPatient = new Patients();

        String patientCode = "";
        PatientType patientType = patientDTO.getPatientType();
        PatientCounter patientCounter = this.patientCounterService.findPatientCounter(patientType);

        if (patientCounter == null) {
            throw new NotFoundException("Patient type not found");
        }

        Long sequence = patientCounter.getCurrentSequence();

        if (patientType == PatientType.Inpatient){
            patientCode = "IP" + String.format("%09d", sequence);
        }

        else if (patientType == PatientType.Outpatient){
            patientCode = "OP" + String.format("%09d", sequence);
        }

        sequence = patientCounter.getCurrentSequence() + 1;
        patientCounter.setCurrentSequence(sequence);
        this.patientCounterRepository.save(patientCounter);

        newPatient.setPcode(patientCode);
        newPatient.setFirstName(patientDTO.getFirstName());
        newPatient.setLastName(patientDTO.getLastName());
        newPatient.setAddress(patientDTO.getAddress());
        newPatient.setDOB(patientDTO.getDob());
        newPatient.setGender(patientDTO.getGender());
        newPatient.setPatientType(patientType);
        newPatient.setPhoneNumber(patientDTO.getPhoneNumber());

        return patient_repo.save(newPatient);
    }
}