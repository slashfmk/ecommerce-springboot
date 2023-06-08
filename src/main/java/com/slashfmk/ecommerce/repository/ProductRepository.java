package com.slashfmk.ecommerce.repository;

import com.slashfmk.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
