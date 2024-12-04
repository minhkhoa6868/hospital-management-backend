package com.dto;

import com.model.HasMedExam.HasMedExam;
import com.model.HasMedExam.HasMedExamId;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HasMedExamDTO {
    private HasMedExamId id;
    private Long examId;
    private Long medCode;
    private int medicationQuantity;

    public HasMedExamDTO(HasMedExam hasMedExam) {
        this.id = hasMedExam.getId();
        this.examId = hasMedExam.getExaminationMedication().getId();
        this.medCode = hasMedExam.getMedicationExamination().getMcode();
        this.medicationQuantity = hasMedExam.getMedicationQuantity();
    }
}
