package com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Set;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.model.Medication_effect;

public interface MedicationEffectRepository extends JpaRepository<Medication_effect, Long> {
    @Query(value = "SELECT * FROM Medication_effect m WHERE m.Med_code = :Mcode", nativeQuery = true)
    Set<Medication_effect> findAllEffects(@Param("Mcode") long Mcode);
}
