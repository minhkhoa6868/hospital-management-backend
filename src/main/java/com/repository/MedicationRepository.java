package com.repository;

import com.model.Medication;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface MedicationRepository extends JpaRepository<Medication,Long>{
    @Query(value = "SELECT * FROM Medication p WHERE p.name = :name", nativeQuery = true)
    Optional<Medication> findMedicationName(@Param("name") String name);


    @Query(value = "SELECT * FROM Medication p WHERE p.mcode = :code", nativeQuery = true)
    Optional<Medication> findMCode(@Param("code") long code);

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=1", nativeQuery = true)
    void enableForeignKeyChecks();
}