package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.model.PatientCounter;
import com.model.Patients.PatientType;
import com.repository.PatientCounterRepository;

@Service
public class PatientCounterService {
    private final PatientCounterRepository patientCounterRepository;

    public PatientCounterService(PatientCounterRepository patientCounterRepository) {
        this.patientCounterRepository = patientCounterRepository;
    }

    // handle find patient counter
    public PatientCounter findPatientCounter(PatientType patientType) {
        Optional<PatientCounter> targetPatientCounter = this.patientCounterRepository.findByPatientType(patientType);

        if (targetPatientCounter.isPresent()) {
            return targetPatientCounter.get();
        } 

        return null;
    }

    // handle create 
    public PatientCounter handleCreatePatientCounter(PatientCounter patientCounter) {
        return this.patientCounterRepository.save(patientCounter);
    }

    // handle get all
    public List<PatientCounter> handleGetAllCounter() {
        return this.patientCounterRepository.findAll();
    }
}
