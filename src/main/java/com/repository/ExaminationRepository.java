package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.Examination;

import jakarta.transaction.Transactional;

@Repository
public interface ExaminationRepository extends JpaRepository<Examination, Long> {
    Optional<Examination> findById(long id);

    @Query(value = "SELECT * FROM Examination e WHERE e.doc_code = :doctorCode", nativeQuery = true)
    List<Examination> findByDoctorCode(long doctorCode);

    @Query(value = "SELECT * FROM Examination e WHERE e.op_code = :patientCode", nativeQuery = true)
    List<Examination> findByPatientCode(long patientCode);

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=1", nativeQuery = true)
    void enableForeignKeyChecks();
}
