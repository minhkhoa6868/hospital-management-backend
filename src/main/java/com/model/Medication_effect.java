package com.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "Medication_effect", uniqueConstraints = @UniqueConstraint(columnNames = {"Med_code", "effect"}))
@Getter
@Setter
public class Medication_effect {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(200)", nullable = false)
    private String effect;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "Med_code", referencedColumnName = "Mcode", nullable = true)
    private Medication medication;
}
