package com.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="Patients", uniqueConstraints = @UniqueConstraint(columnNames = "Pcode"))
@Getter
@Setter
public class Patients{
    
    public enum PatientType{
        Inpatient,
        Outpatient;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(11)", unique = true)
    private String Pcode;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String firstName;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Gender gender;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate DOB;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String address;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PatientType patientType;

    @Column(columnDefinition = "VARCHAR(15)", nullable = true)
    private String phoneNumber;

    @OneToMany(mappedBy = "examinePatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Examination> examinations = new HashSet<>();

    @OneToMany(mappedBy = "takeCarePatient", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HospitalizationInformation> hospitalizationInformations = new HashSet<>();
}