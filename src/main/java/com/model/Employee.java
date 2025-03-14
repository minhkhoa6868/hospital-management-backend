package com.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Employee", uniqueConstraints = {
        @UniqueConstraint(columnNames = "Ecode")
})
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Ecode;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String firstName;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String lastName;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate DOB;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(columnDefinition = "DATE", nullable = false)
    private LocalDate startDate;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String address;

    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String speName;

    @Column(columnDefinition = "INTEGER", nullable = false)
    private long speDegreeYear;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Employee_type employeeType;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String phone_number;

    @ManyToOne
    @JoinColumn(name = "Dno", referencedColumnName = "Dcode", nullable = true)
    private Department department;

    @OneToMany(mappedBy = "examineDoctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Examination> examinations = new HashSet<>();

    @OneToMany(mappedBy = "takeCareNurse", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<HospitalizationInformation> hospitalizationInformations = new HashSet<>();

    @OneToMany(mappedBy = "treatDoctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Treatment> treatments = new HashSet<>();
}
