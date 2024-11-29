package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



@Repository
public interface MedicationRepository extends JpaRepository<Medication,long>{
    @Query(value = "SELECT * FROM Medication p WHERE p.Name = :Name", nativeQuery = true)
    Optional<Medication> findPCode(@Param("Name") String name);


    @Query(value = "SELECT * FROM Medication p WHERE p.MCode = :code", nativeQuery = true)
    List<Medication> findMedication(@Param("code") Integer MCode);

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=1", nativeQuery = true)
    void enableForeignKeyChecks();
}