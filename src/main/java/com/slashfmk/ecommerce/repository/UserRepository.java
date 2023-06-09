package com.slashfmk.ecommerce.repository;

import com.slashfmk.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository <User, Long> {

    public Optional<User> findFirstByEmailAndUsername(String email, String username);
    public Optional<User> findUserByUsername(String username);

}
