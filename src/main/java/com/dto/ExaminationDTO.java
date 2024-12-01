package com.dto;

import java.time.LocalDate;

import com.model.Examination;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ExaminationDTO {
    private LocalDate date;
    private LocalDate nextDate;
    private int fee;
    private String diagnose;
    private Long doctorCode;
    private String doctorName;
    private String patientCode;
    private String patientName;

    public ExaminationDTO(Examination examination) {
        this.date = examination.getDate();
        this.nextDate = examination.getNextDate();
        this.fee = examination.getFee();
        this.diagnose = examination.getDiagnose();
        if (examination.getExamineDoctor() == null) {
            this.doctorCode = null;
            this.doctorName = null;
        }

        else {
            this.doctorCode = examination.getExamineDoctor().getEcode();
            this.doctorName = examination.getExamineDoctor().getFirstName() + " " + examination.getExamineDoctor().getLastName();
        }

        if (examination.getExaminePatient() == null) {
            this.patientCode = null;
            this.patientName = null;
        }

        else {
            this.patientCode = examination.getExaminePatient().getPcode();
            this.patientName = examination.getExaminePatient().getFirstName() + " " + examination.getExaminePatient().getLastName();
        }
    }
}