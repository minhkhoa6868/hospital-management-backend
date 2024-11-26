package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.model.HospitalizationInformation;

import jakarta.transaction.Transactional;

@Repository
public interface HospitalizationInformationRepository extends JpaRepository<HospitalizationInformation, Long> {
    Optional<HospitalizationInformation> findById(Long id);

    //List<HospitalizationInformation> findByPatientId(Long patientId);

    @Query(value = "SELECT * FROM Hospitalization_information hi WHERE hi.nurse_code = :nurseCode", nativeQuery = true)
    List<HospitalizationInformation> findByNurseCode(Long nurseCode);

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=1", nativeQuery = true)
    void enableForeignKeyChecks();
}
