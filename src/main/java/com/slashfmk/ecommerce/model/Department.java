package com.slashfmk.ecommerce.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Department {

    @Id()
    @SequenceGenerator(
            name = "department_sequence",
            sequenceName = "department_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "department_sequence"
    )
    @Column(
            name = "department_id"
    )
    private Long id;
    private String name;
    private String description;

    private LocalDateTime createdAt;

    @OneToMany(
            mappedBy = "department",
            orphanRemoval = true,
            cascade = {CascadeType.PERSIST, CascadeType.MERGE}
    )
    private List<Product> products = new ArrayList<>();

    public Department(String name, String description) {
        this.name = name;
        this.description = description;
        this.createdAt = LocalDateTime.now();
    }
}
