package com.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import com.model.Patients.PatientType;

@Entity
@Table(name = "Patient_counter")
@Getter
@Setter
public class PatientCounter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private PatientType patientType;

    @Column(columnDefinition = "BIGINT", nullable = false)
    private long currentSequence;
}
