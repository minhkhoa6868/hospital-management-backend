package com.model;
import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.presistence.*;

@Entity
@Table(name="Patients", schema = "bkproject")
public class Patients{
    
    public enum PatientType{
        INPATIENT,
        OUTPATIENT;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long PCode;

    @Column(nullable = false, name = "first_name", length = 50)
    private String first_name;

    @Column(nullable = false, name = "last_name", length = 50)
    private String last_name;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(nullable = false, name = "DoB")
    @Temporal(TemporalType.DATE)
    private Date DoB;

    @Column(length = 200)
    private String Address;

    @Enumerated(EnumType.STRING)
    @Column(name = "Patient Type", nullable = false)
    private PatientType patient_type;
}