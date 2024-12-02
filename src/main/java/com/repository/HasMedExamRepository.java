package com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

// import com.model.Examination;
import com.model.Medication;
import com.model.HasMedExam.HasMedExam;
import com.model.HasMedExam.HasMedExamId;

@Repository
public interface HasMedExamRepository extends JpaRepository<HasMedExam, HasMedExamId> {
    @Query("SELECT h.medicationExamination FROM HasMedExam h WHERE h.examinationMedication.id = :examId")
    List<Medication> findMedicationsByExamId(@Param("examId") long examId);



//     @Query(value = "SELECT * FROM Has_Med_Exam h WHERE h.medCode = :medCode", nativeQuery = true)
//     List<Examination> findByMedCode(@Param("medCode") long medCode);
}
