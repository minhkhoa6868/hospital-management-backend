package com.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import jakarta.persistence.GenerationType;

@Entity
@Table(name = "Employee")
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

    @Column(columnDefinition = "YEAR", nullable = false)
    private long speDegreeYear;

    @Column(columnDefinition = "VARCHAR(10)", nullable = false)
    private String employeeType;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee_phone> phone_numbers = new HashSet<>();
}
