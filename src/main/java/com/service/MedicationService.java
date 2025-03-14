package com.service;

import com.model.Medication;
import com.model.Medication_effect;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dto.MedicationDTO;
import com.repository.MedicationRepository;

@Service
@Transactional
public class MedicationService{
    private final MedicationRepository medRepo;

    //initialize
    public MedicationService(MedicationRepository medRepo){
        this.medRepo = medRepo;
    }

    //find by MCode
    @Transactional
    public Medication findMedication(long code){
        Optional<Medication> targetMedication = this.medRepo.findMCode(code);

        if (targetMedication.isPresent()){
            return targetMedication.get();
        }
        return null;
    }

    @Transactional
    public List<MedicationDTO> handleGetAllMedication(){
        List<Medication> Medications = medRepo.findAll();

        return Medications.stream().map(MedicationDTO::new).toList();
    }

    public MedicationDTO getOneMedication(long code){
        Medication Medication = this.findMedication(code);

        if (Medication == null){
            return null;
        }
        return new MedicationDTO(Medication);
    }

    //create new Medication
    @Transactional
    public Medication createMedication(MedicationDTO medicationDTO){
        Medication newMedication = new Medication();

        newMedication.setName(medicationDTO.getName());
        newMedication.setPrice(medicationDTO.getPrice());
        newMedication.setQuantity(medicationDTO.getQuantity());
        newMedication.setExpirationDate(medicationDTO.getExpirationDate());
        newMedication.setStatus(medicationDTO.getStatus());

        // handle create phone number
        if (medicationDTO.getEffects() != null && !medicationDTO.getEffects().isEmpty()) {
            for (String effect : medicationDTO.getEffects()) {
                // Avoid duplicates or null values
                if (effect != null && !effect.trim().isEmpty()) {
                    Medication_effect newEffect = new Medication_effect();
                    newEffect.setEffect(effect.trim());
                    newEffect.setMedication(newMedication); // Establish relationship
                    newMedication.getEffects().add(newEffect); // Add to medication
                }
            }
        }

        else {
            newMedication.setEffects(null);
        }

        return medRepo.save(newMedication);
    }
}