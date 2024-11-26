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
    private Long docCode;

    public ExaminationDTO(Examination examination) {
        this.date = examination.getDate();
        this.nextDate = examination.getNextDate();
        this.fee = examination.getFee();
        this.diagnose = examination.getDiagnose();
        if (examination.getDoctor() == null) {
            this.docCode = null;
        }

        else {
            this.docCode = examination.getDoctor().getEcode();
        }
    }
}
