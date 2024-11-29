package com.service;

import com.model.Medication;

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
    public Medication createMedication(MedicationDTO MedicationDTO){
        Medication newMedication = new Medication();

        this.medRepo.disableForeignKeyChecks();

        newMedication.setName(MedicationDTO.getName());
        newMedication.setPrice(MedicationDTO.getPrice());
        newMedication.setQuantity(MedicationDTO.getQuantity());
        newMedication.setExpirationDate(MedicationDTO.getExpirationDate());
        newMedication.setStatus(MedicationDTO.getStatus());

        this.medRepo.enableForeignKeyChecks();
        return medRepo.save(newMedication);
    }
}