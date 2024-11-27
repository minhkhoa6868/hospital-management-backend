package com.model;

import java.time.LocalDate;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Treatment")
@Getter
@Setter
public class Treatment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(255)", nullable = false)
    private String result;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate startDate;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code", referencedColumnName = "Ecode", nullable = true)
    private Employee treatDoctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hos_info_id", referencedColumnName = "id", nullable = true)
    private HospitalizationInformation treatInformation;
}
