package com.slashfmk.ecommerce.repository;

import com.slashfmk.ecommerce.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository <Address, Long> {
}
