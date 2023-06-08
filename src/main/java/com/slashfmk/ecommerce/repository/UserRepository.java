package com.slashfmk.ecommerce.repository;

import com.slashfmk.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository <User, Long> {

}
