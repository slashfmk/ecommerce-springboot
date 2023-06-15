package com.slashfmk.ecommerce.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Address {

    @Id
    @SequenceGenerator(
            sequenceName = "address_sequence",
            name = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "address_sequence"
    )
    private Long id;
    private String address;
    private String state;
    private String zip;
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserId")
    @JsonBackReference
    private User user;

    private LocalDateTime createdAt;

    public Address(String address, String state, String zip, String country) {
        this.address = address;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.createdAt = LocalDateTime.now();
    }
}
