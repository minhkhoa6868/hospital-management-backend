package com.dto;

import java.time.LocalDate;

import com.model.Gender;
import com.model.Patients;
import com.model.Patients.PatientType;
import java.util.List;
import java.util.stream.Collectors;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientDTO {
    private String pcode;
    private String firstName, lastName;
    private LocalDate dob;
    private Gender gender;
    private String address;
    private PatientType patient_type;
    private List<ExaminationDTO> examinations;
    private List<HospitalizationInformationDTO> hospitalizationInformation;

    public PatientDTO(Patients patient) {
        this.pcode = patient.getPcode();
        this.firstName = patient.getFirstName();
        this.lastName = patient.getLastName();
        this.dob = patient.getDOB();
        this.gender = patient.getGender();
        this.address = patient.getAddress();
        this.patient_type = patient.getPatientType();

        if (patient.getExaminations() == null) {
            this.examinations = null;
        }

        else {
            this.examinations = patient.getExaminations().stream().map(ExaminationDTO::new)
                    .collect(Collectors.toList());
        }

        if (patient.getHospitalizationInformations() == null) {
            this.hospitalizationInformation = null;
        }

        else {
            this.hospitalizationInformation = patient.getHospitalizationInformations().stream()
                    .map(HospitalizationInformationDTO::new).collect(Collectors.toList());
        }
    }
}