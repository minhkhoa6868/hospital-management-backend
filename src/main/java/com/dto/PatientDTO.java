package com.dto;

import com.model.Patients.PatientType;

@Getter
@Setter
public class PatientDTO{
    private Long pcode;
    private String firstname, lastname;
    private Gender gender;
    private Date dob;
    private String adress;
    private PatientType patient_type;

    public PatientDTO(Patient patient){
        this.pcode = patient.getPCode();
        this.firstname = patient.getFirstname();
        this.lastname = patient.getLastname();
        this.dob = patient.getDoB();
        this.gender = patient.getGender();
        this.adress = patient.getAdress();
        patient_type = patient.getPatienttype();
    }
}   