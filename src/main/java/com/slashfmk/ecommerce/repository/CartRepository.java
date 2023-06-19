package com.slashfmk.ecommerce.repository;

import com.slashfmk.ecommerce.model.Cart;
import com.slashfmk.ecommerce.model.Product;
import com.slashfmk.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository <Cart, Long> {

    @Query("select c from Cart c where c.user = ?1")
    Optional<List<Cart>> getAllProduct(Long userId);


//    public Optional<List<Cart>> getAllItems(Long id);

    public Optional<List<Cart>> findCartById(Long id);

}
