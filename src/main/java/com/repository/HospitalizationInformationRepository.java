package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.HospitalizationInformation;
import com.model.Patients;

import jakarta.transaction.Transactional;

@Repository
public interface HospitalizationInformationRepository extends JpaRepository<HospitalizationInformation, Long> {
    Optional<HospitalizationInformation> findById(Long id);

    //List<HospitalizationInformation> findByPatientId(Long patientId);

    @Query(value = "SELECT * FROM Hospitalization_information hi WHERE hi.nurse_code = :nurseCode", nativeQuery = true)
    List<HospitalizationInformation> findByNurseCode(Long nurseCode);

    @Query(value = "SELECT * FROM Hospitalization_information hi WHERE hi.ip_code = :patientCode", nativeQuery = true)
    List<Long> findByPatientCode(String patientCode);

    @Query("SELECT hi.id FROM HospitalizationInformation hi JOIN hi.treatments t WHERE t.id IN :treatId")
    List<Long> findByTreatmentId(@Param("treatId") List<Long> treatId);

    // find all patient code by hos_info_id
    @Query(value = "SELECT hi.ip_code FROM Hospitalization_information hi WHERE hi.id IN (:hos_info_id)", nativeQuery = true)
    List<String> findPatientCodeByHosInfoId(List<Long> hos_info_id);

    // find all patient by hos_info id
    @Query(value = "SELECT * FROM Patients p WHERE p.pcode IN (:pcode)", nativeQuery = true)
    List<Patients> findByHosInfoId(@Param("pcode") List<String> pcode);

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=1", nativeQuery = true)
    void enableForeignKeyChecks();
}
