package com.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Patients")
@Getter
@Setter
public class Patients{
    
    public enum PatientType{
        Inpatient,
        Outpatient;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PCode;

    @Column(nullable = false, name = "first_name", length = 50)
    private String first_name;

    @Column(nullable = false, name = "last_name", length = 50)
    private String last_name;

    // @Column(nullable = false)
    // @Enumerated(EnumType.STRING)
    // private Gender gender;

    @Column(nullable = false)
    private LocalDate DOB;

    @Column(nullable = false)
    private String Address;

    @Enumerated(EnumType.STRING)
    @Column(name = "Patient Type", nullable = false)
    private PatientType patient_type;
}