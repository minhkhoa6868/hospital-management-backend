package com.dto;

import java.time.LocalDate;
import java.util.List;

import com.model.Medication;
import com.model.MedStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MedicationDTO {
    private long Mcode;
    private String name;
    private int price;
    private int quantity;
    private LocalDate expirationDate;
    private MedStatus status;
    private List<String> effects;

    public MedicationDTO(Medication med) {
        this.Mcode = med.getMcode();
        this.name = med.getName();
        this.price = med.getPrice();
        this.quantity = med.getQuantity();
        this.expirationDate = med.getExpirationDate();
        this.status = med.getStatus();

        if (med.getEffects() == null) {
            this.effects = null;
        }

        else {
            this.effects = med.getEffects().stream().map(effect -> effect.getEffect()).toList();
        }
    }
}