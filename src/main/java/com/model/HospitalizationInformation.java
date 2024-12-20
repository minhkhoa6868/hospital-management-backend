package com.model;

import java.time.LocalDate;
import java.util.Set;
import java.util.HashSet;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Hospitalization_Information")
@Getter
@Setter
public class HospitalizationInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate dateOfAdmission;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate dateOfDischarge;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String diagnosis;

    @Column(columnDefinition = "INT", nullable = false)
    private int fee;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String sickroom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nurse_code", referencedColumnName = "Ecode", nullable = true)
    private Employee takeCareNurse;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ip_code", referencedColumnName = "Pcode", nullable = true)
    private Patients takeCarePatient;

    @OneToMany(mappedBy = "treatInformation", cascade = CascadeType.ALL)
    private Set<Treatment> treatments = new HashSet<>();
}
