package com.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.HashSet;
import java.util.Set;

import com.model.HasMedExam.HasMedExam;
import com.model.HasMedTreatment.HasMedTreatment;

@Entity
@Table(name="Medication")
@Getter
@Setter
public class Medication{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Mcode;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String name;

    @Column(columnDefinition = "INT", nullable = false)
    private int price;

    @Column(columnDefinition = "INT", nullable = false)
    private int quantity;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate expirationDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MedStatus status;  

    @OneToMany(mappedBy = "medication", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Medication_effect> effects = new HashSet<>();

    @OneToMany(mappedBy = "medicationExamination", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HasMedExam> hasMedExams = new HashSet<>();

    @OneToMany(mappedBy = "medicationTreatment", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HasMedTreatment> hasMedTreaments = new HashSet<>();
}