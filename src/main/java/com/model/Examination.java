package com.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.model.HasMedExam.HasMedExam;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Examination")
@Getter
@Setter
public class Examination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate date;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate nextDate;

    @Column(columnDefinition = "INT", nullable = false)
    private int fee;

    @Column(columnDefinition = "VARCHAR(225)", nullable = false)
    private String diagnose;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doc_code", referencedColumnName = "Ecode", nullable = true)
    private Employee examineDoctor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "op_code", referencedColumnName = "Pcode", nullable = true)
    private Patients examinePatient;

    @OneToMany(mappedBy = "examinationMedication", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HasMedExam> hasMedExams = new HashSet<>();
}
