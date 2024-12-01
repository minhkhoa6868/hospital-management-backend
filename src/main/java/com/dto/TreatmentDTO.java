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
    private LocalDate informationDateOfAdmission;
    private LocalDate informationDateOfDischarge;
    private String informationDiagnosis;
    private Integer informationFee;
    private String informationSickroom;
    private Long takeCareNurseCode;
    private String takeCareNurseName;
    private String patientCode;
    private String patientName;

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
            this.informationDateOfAdmission = null;
            this.informationDateOfDischarge = null;
            this.informationDiagnosis = null;
            this.informationFee = null;
            this.informationSickroom = null;
        }

        else {
            this.informationId = treatment.getTreatInformation().getId();
            this.informationDateOfAdmission = treatment.getTreatInformation().getDateOfAdmission();
            this.informationDateOfDischarge = treatment.getTreatInformation().getDateOfDischarge();
            this.informationDiagnosis = treatment.getTreatInformation().getDiagnosis();
            this.informationFee = treatment.getTreatInformation().getFee();
            this.informationSickroom = treatment.getTreatInformation().getSickroom();
        }

        if (treatment.getTreatInformation().getTakeCareNurse() == null) {
            this.takeCareNurseCode = null;
            this.takeCareNurseName = null;
        }

        else {
            this.takeCareNurseCode = treatment.getTreatInformation().getTakeCareNurse().getEcode();
            this.takeCareNurseName = treatment.getTreatInformation().getTakeCareNurse().getFirstName() + " " + treatment.getTreatInformation().getTakeCareNurse().getLastName();
        }

        if (treatment.getTreatInformation().getTakeCarePatient() == null) {
            this.patientCode = null;
            this.patientName = null;
        }

        else {
            this.patientCode = treatment.getTreatInformation().getTakeCarePatient().getPcode();
            this.patientName = treatment.getTreatInformation().getTakeCarePatient().getFirstName() + " " + treatment.getTreatInformation().getTakeCarePatient().getLastName();
        }
    }
}
