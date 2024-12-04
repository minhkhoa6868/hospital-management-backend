package com.model.HasMedExam;

import com.model.Examination;
import com.model.Medication;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Has_Med_Exam")
@Getter
@Setter
public class HasMedExam {
    @EmbeddedId
    private HasMedExamId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("medCode")
    @JoinColumn(name = "med_code")
    private Medication medicationExamination;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("examId")
    @JoinColumn(name = "exam_id")
    private Examination examinationMedication;

    @Column(columnDefinition = "INT", nullable = false)
    private int medicationQuantity;
}