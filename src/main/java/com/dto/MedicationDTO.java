package com.dto;

import main.java.com.model.MedStatus;

@Getter
@Setter
public class MedicationDTO{
    private Integer MCode;
    private String Name;
    private Integer Price;
    private Integer Quantity;
    private Date ExpirationDate;
    private MedStatus Status;

    public MedicationDTO(Medicaton med){
        this.MCode = med.getMCode();
        this.Name = med.getName();
        this.Price = med.getPrice();
        this.Quantity = med.getQuantity;
        this.ExpirationDate = med.getExpirationDate();
        this.Status = med.getStatus();
    }
}   