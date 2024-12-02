package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Medication;
import com.model.HasMedTreatment.HasMedTreatment;
import com.model.HasMedTreatment.HasMedTreatmentId;

public interface HasMedTreatmentRepository extends JpaRepository<HasMedTreatment, HasMedTreatmentId> {
    @Query("SELECT h.medicationTreatment FROM HasMedTreatment h WHERE h.treatmentMedication.id = :treatId")
    List<Medication> findMedicationsByTreatmentId(@Param("treatId") long treatId);

}
