package com.dto;

import java.time.LocalDate;

import com.model.Medication;
import com.model.MedStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationDTO{
    private long MCode;
    private String Name;
    private int Price;
    private int Quantity;
    private LocalDate ExpirationDate;
    private MedStatus Status;

    public MedicationDTO(Medication med){
        this.MCode = med.getMCode();
        this.Name = med.getName();
        this.Price = med.getPrice();
        this.Quantity = med.getQuantity();
        this.ExpirationDate = med.getExpirationDate();
        this.Status = med.getStatus();
    }
}   