package com.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Set;
import java.util.HashSet;
import jakarta.persistence.CascadeType;

@Entity
@Table(name = "Department", uniqueConstraints = {
    @UniqueConstraint(columnNames = "title"),
    @UniqueConstraint(columnNames = "Dcode")
})
@Getter
@Setter
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Dcode;

    @Column(columnDefinition = "VARCHAR(225)")
    private String title;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Employee> employees = new HashSet<>();

    @OneToOne
    @JoinColumn(name = "Dean_code", referencedColumnName = "Ecode", nullable = true)
    private Employee dean;
}
