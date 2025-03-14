package com.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Patients;

@Repository
public interface PatientRepository extends JpaRepository<Patients,Long>{
    @Query(value = "SELECT * FROM Patients p WHERE p.pcode = :PCode", nativeQuery = true)
    Optional<Patients> findPCode(@Param("PCode") String PCode);
}