package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.HasMedTreatmentDTO;
import com.dto.MedicationDTO;
import com.exception.NotFoundException;
import com.model.Medication;
import com.model.Treatment;
import com.model.HasMedTreatment.HasMedTreatment;
import com.model.HasMedTreatment.HasMedTreatmentId;
import com.repository.HasMedTreatmentRepository;
import com.repository.MedicationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HasMedTreatmentService {
    private final HasMedTreatmentRepository hasMedTreatmentRepository;
    private final MedicationService medicationService;
    private final MedicationRepository medicationRepository;
    private final TreatmentService treatmentService;

    public HasMedTreatmentService(HasMedTreatmentRepository hasMedTreatmentRepository, MedicationService medicationService, TreatmentService treatmentService, MedicationRepository medicationRepository) {
        this.hasMedTreatmentRepository = hasMedTreatmentRepository;
        this.medicationService = medicationService;
        this.treatmentService = treatmentService;
        this.medicationRepository = medicationRepository;
    }

    // hande create HasMedTreatmentService
    public HasMedTreatmentDTO handleCreateHasMedTreatment(HasMedTreatment hasMedTreatment) {
        if (hasMedTreatment.getMedicationTreatment() == null) {
            throw new IllegalArgumentException("Medication information is missing");
        }

        if (hasMedTreatment.getTreatmentMedication() == null) {
            throw new IllegalArgumentException("Treatment information is missing");
        }

        Medication medication = this.medicationService.findMedication(hasMedTreatment.getMedicationTreatment().getMcode());

        if (medication == null) {
            throw new NotFoundException("Medication not found");
        }

        Treatment treatment = this.treatmentService.findTreatment(hasMedTreatment.getTreatmentMedication().getId());

        if (treatment == null) {
            throw new NotFoundException("Treatment not found");
        }

        HasMedTreatmentId hasMedTreatmentId = new HasMedTreatmentId();
        hasMedTreatmentId.setMedCode(medication.getMcode());
        hasMedTreatmentId.setTreatId(treatment.getId());

        HasMedTreatment newHasMedTreatment = new HasMedTreatment();
        newHasMedTreatment.setId(hasMedTreatmentId);
        newHasMedTreatment.setMedicationTreatment(medication);
        newHasMedTreatment.setTreatmentMedication(treatment);
        newHasMedTreatment.setMedicationQuantity(hasMedTreatment.getMedicationQuantity());

        // update quantity of medication
        medication.setQuantity(medication.getQuantity() - hasMedTreatment.getMedicationQuantity());
        this.medicationRepository.save(medication);

        HasMedTreatment savedHasMedTreatment =  this.hasMedTreatmentRepository.save(newHasMedTreatment);

        return new HasMedTreatmentDTO(savedHasMedTreatment);
    }

    // handle get all medication by treatId
    public List<MedicationDTO> handleGetAllMedicationByTreatId(long treatId) {
        Treatment treatment = this.treatmentService.findTreatment(treatId);

        if (treatment == null) {
            throw new NotFoundException("Medication not found");
        }

        List<Medication> medications = this.hasMedTreatmentRepository.findMedicationsByTreatmentId(treatId);

        return medications.stream().map(MedicationDTO::new).toList();
    }
}
