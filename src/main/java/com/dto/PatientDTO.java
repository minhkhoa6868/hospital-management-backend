package com.dto;

import java.time.LocalDate;

import com.model.Patients;
import com.model.Patients.PatientType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO{
    private Long pcode;
    private String firstName, lastName;
    private LocalDate dob;
    private String address;
    private PatientType patient_type;

    public PatientDTO(Patients patient){
        this.pcode = patient.getPCode();
        this.firstName = patient.getFirst_name();
        this.lastName = patient.getLast_name();
        this.dob = patient.getDOB();
        this.address = patient.getAddress();
        patient_type = patient.getPatient_type();
    }
}   