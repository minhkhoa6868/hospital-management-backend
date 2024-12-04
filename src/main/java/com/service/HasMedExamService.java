package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.dto.HasMedExamDTO;
import com.dto.MedicationDTO;
import com.exception.NotFoundException;
import com.model.Examination;
import com.model.MedStatus;
import com.model.Medication;
import com.model.HasMedExam.HasMedExam;
import com.model.HasMedExam.HasMedExamId;
import com.repository.HasMedExamRepository;
import com.repository.MedicationRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class HasMedExamService {
    private final HasMedExamRepository hasMedExamRepository;
    private final ExaminationService examinationService;
    private final MedicationService medicationService;
    private final MedicationRepository medicationRepository;

    public HasMedExamService(HasMedExamRepository hasMedExamRepository, ExaminationService examinationService,
            MedicationService medicationService, MedicationRepository medicationRepository) {
        this.hasMedExamRepository = hasMedExamRepository;
        this.examinationService = examinationService;
        this.medicationService = medicationService;
        this.medicationRepository = medicationRepository;
    }

    // handle create HasMedExam
    public HasMedExamDTO handleCreateHasMedExam(HasMedExam hasMedExam) {
        if (hasMedExam.getExaminationMedication() == null) {
            throw new IllegalArgumentException("Examination information is missing");
        }
        if (hasMedExam.getMedicationExamination() == null) {
            throw new IllegalArgumentException("Medication information is missing");
        }

        // Accessing the correct examId
        Examination examination = this.examinationService.findExamination(
                hasMedExam.getExaminationMedication().getId());

        if (examination == null) {
            throw new NotFoundException("Examination not found");
        }

        Medication medication = this.medicationService.findMedication(
                hasMedExam.getMedicationExamination().getMcode());

        if (medication == null) {
            throw new NotFoundException("Medication not found");
        }

        HasMedExamId hasMedExamId = new HasMedExamId();
        hasMedExamId.setExamId(examination.getId());
        hasMedExamId.setMedCode(medication.getMcode());

        HasMedExam newHasMedExam = new HasMedExam();
        newHasMedExam.setId(hasMedExamId);
        newHasMedExam.setExaminationMedication(examination);
        newHasMedExam.setMedicationExamination(medication);
        newHasMedExam.setMedicationQuantity(hasMedExam.getMedicationQuantity());

        // update medication quantity
        if (medication.getQuantity() < hasMedExam.getMedicationQuantity()) {
            throw new IllegalArgumentException("Medication quantity is not enough");
        }

        if (medication.getStatus() == MedStatus.Out_of_stock) {
            throw new IllegalArgumentException("Medication is out of stock");
        }

        else if (medication.getStatus() == MedStatus.Expired) {
            throw new IllegalArgumentException("Medication is expired");
        }

        else {
            int remainingQuantity = medication.getQuantity() - hasMedExam.getMedicationQuantity();

            if (remainingQuantity == 0) {
                medication.setStatus(MedStatus.Out_of_stock);
            }

            medication.setQuantity(medication.getQuantity() - hasMedExam.getMedicationQuantity());

            this.medicationRepository.save(medication);
        }

        HasMedExam savedHasMedExam = this.hasMedExamRepository.save(newHasMedExam);

        return new HasMedExamDTO(savedHasMedExam);
    }

    // handle get all medication by examId
    public List<MedicationDTO> getAllMedicationByExamId(long examId) {
        Examination examination = this.examinationService.findExamination(examId);

        if (examination == null) {
            throw new NotFoundException("Examination not found");
        }

        List<Medication> medications = this.hasMedExamRepository.findMedicationsByExamId(examId);

        return medications.stream().map(MedicationDTO::new).toList();
    }
}
