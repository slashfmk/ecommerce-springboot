package com.slashfmk.ecommerce.service;


import com.slashfmk.ecommerce.model.User;
import com.slashfmk.ecommerce.repository.UserRepository;
import com.slashfmk.ecommerce.security.SecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;

    public User registerUser(User user) {

        var userExists = this.userRepository.findFirstByEmailAndUsername(user.getEmail(), user.getUsername());

        if (userExists.isPresent())
            throw new IllegalArgumentException("A user with the current email or username exits");

        user.setPassword(this.encodedPassword(user.getPassword()));
        this.userRepository.save(user);

        return user;
    }


    public User updateUser(User userNewInfo) {

        var userExists = this.userRepository
                .findFirstByEmailAndUsername(userNewInfo.getEmail(), userNewInfo.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException(userNewInfo.getUsername() + " not found!"));

        this.userRepository.save(userExists);
        return userExists;
    }

    public User getUser(Long id) {
        return this.userExists(id);
    }


    public User deleteUser(Long id) {

        var userExist = this.userExists(id);
        this.userRepository.delete(userExist);
        return userExist;
    }


    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    // Encode password using Bcrypt
    private String encodedPassword(String password) {
        return this.securityConfig.passwordEncoder().encode(password);
    }

    /**
     * Util
     * Check if user exists by id
     */
    private User userExists(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("The user doesn't exist"));
    }

}