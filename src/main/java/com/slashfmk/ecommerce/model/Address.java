package com.slashfmk.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @Id
    @SequenceGenerator(
            sequenceName = "address_sequence",
            name = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    private Long id;
    private String address;
    private String state;
    private String zip;
    private String country;

    @ManyToOne
    @JoinColumn(
            name = "user_id",
            nullable = false,
            referencedColumnName = "user_id",
            foreignKey = @ForeignKey(name = "user_address_fk")
    )
    private User user;

    private LocalDateTime createdAt;

    public Address(String address, String state, String zip, String country, User user) {
        this.address = address;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.user = user;
        this.createdAt = LocalDateTime.now();
    }
}
