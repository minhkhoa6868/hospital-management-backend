package com.dto;

import java.time.LocalDate;

import com.model.HospitalizationInformation;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalizationInformationDTO {
    private Long id;
    private LocalDate dateOfAdmission;
    private LocalDate dateOfDischarge;
    private String dianosis;
    private int fee;
    private String sickroom;
    private Long nurseCode;
    private String nurseName;

    public HospitalizationInformationDTO(HospitalizationInformation hi) {
        this.id = hi.getId();
        this.dateOfAdmission = hi.getDateOfAdmission();
        this.dateOfDischarge = hi.getDateOfDischarge();
        this.dianosis = hi.getDiagnosis();
        this.fee = hi.getFee();
        this.sickroom = hi.getSickroom();
        if (hi.getTakeCareNurse() == null) {
            this.nurseCode = null;
        }

        else {
            this.nurseCode = hi.getTakeCareNurse().getEcode();
        }

        if (hi.getTakeCareNurse() == null) {
            this.nurseName = null;
        }
        else {
            this.nurseName = hi.getTakeCareNurse().getFirstName() + " " + hi.getTakeCareNurse().getLastName();
        }
    }
}
