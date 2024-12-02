package com.model.HasMedTreatment;

import com.model.Medication;
import com.model.Treatment;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Has_Med_Treatment")
@Getter
@Setter
public class HasMedTreatment {
    @EmbeddedId
    private HasMedTreatmentId id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("medCode")
    @JoinColumn(name = "med_code")
    private Medication medicationTreatment;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("treatId")
    @JoinColumn(name = "treat_id")
    private Treatment treatmentMedication;

    @Column(columnDefinition = "INT", nullable = false)
    private int medicationQuantity;
}
