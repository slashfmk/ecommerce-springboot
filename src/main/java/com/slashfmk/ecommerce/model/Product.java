package com.slashfmk.ecommerce.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class Product {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "product_sequence"
    )
    @SequenceGenerator(
            name = "product_sequence",
            allocationSize = 1,
            sequenceName = "product_sequence"
    )
    private Long id;
    private String name;
    private String description;
    @Column(
            nullable = false
    )
    private Double price;

    @ManyToOne
    @JoinColumn(
            referencedColumnName = "department_id",
            name = "department_id",
            nullable = false,
            foreignKey = @ForeignKey(name = "product_department_fk")
    )
    private Department department;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)

    private List<Cart> users = new ArrayList<>();

    public Product(String name, String description, Double price, Department department) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.department = department;
    }
}
