package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.model.PatientCounter;
import com.model.Patients.PatientType;

@Repository
public interface PatientCounterRepository extends JpaRepository<PatientCounter, Long> {
    Optional<PatientCounter> findByPatientType(PatientType patientType);
}
