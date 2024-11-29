package com.service;

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
    public Medication findMedication(integer code){
        Optional<Medication> targetMedication = this.medRepo.findMCode(code);

        if (targetMedication.isPresent()){
            return targetMedication.get();
        }
        return null;
    }

    @Transactional
    public List<Medication> getAllMedication(Medication Medication){
        List<Medication> Medications = medRepo.findAll();

        return Medications.stream().map(MedicationDTO::new).toList();
    }

    public MedicationDTO getOneMedication(integer code){
        Medication Medication = this.findMedication(code);

        if (Medication == null){
            throw new RuntumeException("Medication not found");
        }
        return new MedicationDTO(Medication);
    }

    //create new Medication
    @Transactional
    public Medication createMedication(MedicationDTO MedicationDTO){
        System.out.println(MedicationDTO);

        Medication newMedication = new Medication();

        this.medRepo.disableForeignKeyChecks();

        newMedication.setName(MedicationDTO.getName());
        newMedication.setPrice(MedicationDTO.getPrice());
        newMedication.setQuantity(MedicationDTO.getQuantity());
        newMedication.setExpirationDate(MedicationDTO.getExpirationDate());
        newMedication.setMedStatus(MedicationDTO.getMedStatus());

        this.medRepo.enableForeignKeyChecks();
        return medRepo.save(newMedication);
    }

    //update Medication info
    @Transactional
    public MedicationDTO updateMedication(integer code, MedicationDTO MedicationDTO){
        Medication updated = this.findMedication(code);

        if (updated==null){
            throw new RuntimeException("Medication not found");
        }

        updated.setName(MedicationDTO.getName());
        updated.setPrice(MedicationDTO.getPrice());
        updated.setQuantity(MedicationDTO.getQuantity());
        updated.setExpirationDate(MedicationDTO.getExpirationDate());
        updated.setMedStatus(MedicationDTO.getMedStatus());
        Medication saved = medRepo.save(updated);
        return new MedicationDTO(saved);
    }

    //clear Medication list
    @Transactional
    public void deleteAllMedications() {
        medRepo.deleteAll();
    }
    
    //delete Medication
    @Transactional
    public void deleteMedication(integer code){
        Medication Medication = this.findMedication(code);
        if (Medication == null) {throw new RuntimeException("Medication not found");}
        medRepo.delete(Medication);
    }
}