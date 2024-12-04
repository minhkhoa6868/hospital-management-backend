package com.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.model.Treatment;

import jakarta.transaction.Transactional;

@Repository
public interface TreatmentRepository extends JpaRepository<Treatment, Long> {
    Optional<Treatment> findById(long id);

    @Query(value = "SELECT * FROM Treatment t WHERE t.doc_code = :doctorCode", nativeQuery = true)
    List<Treatment> findByDoctorCode(long doctorCode);

    @Query(value = "SELECT * FROM Treatment t WHERE t.hos_info_id IN (:informationIds)", nativeQuery = true)
    List<Treatment> findByInformationId(@Param("informationIds") List<Long> informationIds);
    
    @Query(value = "SELECT t.id FROM Treatment t WHERE t.doc_code IN (:doctorCode)", nativeQuery = true)
    List<Long> findByDocCode(long doctorCode);

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=0", nativeQuery = true)
    void disableForeignKeyChecks();

    @Modifying
    @Transactional
    @Query(value = "SET FOREIGN_KEY_CHECKS=1", nativeQuery = true)
    void enableForeignKeyChecks();
}
