package com.repository;

import java.util.List;
import java.util.Optional;

import com.model.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient,long>{
    @Query(value = "SELECT * FROM Patient p WHERE p.PCode = :PCode", nativeQuery = true)
    Optional<Patient> findPCode(@Param("PCode") long PCode);

    Optional<Patient> findTitle(String title);

    @Query(value = "SELECT * FROM Patient p WHERE p.DoB = :DoB")
    List<Patient> findDoB(@Param("DoB") Date DoB)
}