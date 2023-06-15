package com.slashfmk.ecommerce.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Cart {

    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cart_sequence"
    )
    @SequenceGenerator(
            allocationSize = 1,
            name = "cart_sequence"
    )
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User user;

    @ManyToOne
    @JoinColumn(name = "productId")
    private Product product;

    private LocalDateTime added_at;

    public Cart(User user, Product product) {
        this.user = user;
        this.product = product;
        this.added_at = LocalDateTime.now();
    }
}
