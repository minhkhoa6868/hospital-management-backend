package com.dto;

import java.time.LocalDate;

import com.model.Treatment;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TreatmentDTO {
    private String result;
    private LocalDate starDate;
    private LocalDate endDate;
    private Long doctorCode;
    private String doctorName;
    private Long informationId;
    private String informationDiagnosis;
    private String informationSickroom;

    public TreatmentDTO(Treatment treatment) {
        this.result = treatment.getResult();
        this.starDate = treatment.getStartDate();
        this.endDate = treatment.getEndDate();
        if (treatment.getTreatDoctor() == null) {
            this.doctorCode = null;
            this.doctorName = null;
        }

        else {
            this.doctorCode = treatment.getTreatDoctor().getEcode();
            this.doctorName = treatment.getTreatDoctor().getFirstName() + " " + treatment.getTreatDoctor().getLastName();
        }

        if (treatment.getTreatInformation() == null) {
            this.informationId = null;
            this.informationDiagnosis = null;
            this.informationSickroom = null;
        }

        else {
            this.informationId = treatment.getTreatInformation().getId();
            this.informationDiagnosis = treatment.getTreatInformation().getDiagnosis();
            this.informationSickroom = treatment.getTreatInformation().getSickroom();
        }
    }
}
