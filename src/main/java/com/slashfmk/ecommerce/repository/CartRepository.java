package com.slashfmk.ecommerce.repository;

import com.slashfmk.ecommerce.model.Cart;
import com.slashfmk.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CartRepository extends JpaRepository <Cart, Long> {

    @Query("")
    public List<Product> getAllProduct();
}
