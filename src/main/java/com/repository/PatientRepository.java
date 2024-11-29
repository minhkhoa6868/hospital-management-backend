package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,long>{
    @Query(value = "SELECT * FROM Patient p WHERE p.PCode = :PCode", nativeQuery = true)
    Optional<Patient> findPCode(@Param("PCode") long PCode);

    Optional<Patient> findTitle(String title);

    @Query(value = "SELECT * FROM Patient p WHERE p.DoB = :DoB")
    List<Patient> findDoB(@Param("DoB") Date DoB)

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=1", nativeQuery = true)
    void enableForeignKeyChecks();
}