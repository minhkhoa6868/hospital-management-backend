package com.dto;

import com.model.HasMedTreatment.HasMedTreatment;
import com.model.HasMedTreatment.HasMedTreatmentId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HasMedTreatmentDTO {
    private HasMedTreatmentId id;
    private Long medCode;
    private Long treatId;
    private int medicationQuantity;

    public HasMedTreatmentDTO(HasMedTreatment hasMedTreatment) {
        this.id = hasMedTreatment.getId();
        this.medCode = hasMedTreatment.getMedicationTreatment().getMcode();
        this.treatId = hasMedTreatment.getTreatmentMedication().getId();
        this.medicationQuantity = hasMedTreatment.getMedicationQuantity();
    }
}
