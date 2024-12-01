package com.dto;

import java.time.LocalDate;
import java.util.List;

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
    private String patientCode;
    private String patientName;
    private List<TreatmentDTO> treatments;

    public HospitalizationInformationDTO(HospitalizationInformation hi) {
        this.id = hi.getId();
        this.dateOfAdmission = hi.getDateOfAdmission();
        this.dateOfDischarge = hi.getDateOfDischarge();
        this.dianosis = hi.getDiagnosis();
        this.fee = hi.getFee();
        this.sickroom = hi.getSickroom();
        if (hi.getTakeCareNurse() == null) {
            this.nurseCode = null;
            this.nurseName = null;
        }

        else {
            this.nurseCode = hi.getTakeCareNurse().getEcode();
            this.nurseName = hi.getTakeCareNurse().getFirstName() + " " + hi.getTakeCareNurse().getLastName();
        }

        if (hi.getTakeCarePatient() == null) {
            this.patientCode = null;
            this.patientName = null;
        }

        else {
            this.patientCode = hi.getTakeCarePatient().getPcode();
            this.patientName = hi.getTakeCarePatient().getFirstName() + " " + hi.getTakeCarePatient().getLastName();
        }

        if (hi.getTreatments() == null) {
            this.treatments = null;
        }

        else {
            this.treatments = hi.getTreatments().stream().map(TreatmentDTO::new).toList();
        }
    }
}
