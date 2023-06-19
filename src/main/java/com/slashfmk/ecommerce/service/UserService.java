package com.slashfmk.ecommerce.service;


import com.slashfmk.ecommerce.model.Cart;
import com.slashfmk.ecommerce.model.User;
import com.slashfmk.ecommerce.repository.CartRepository;
import com.slashfmk.ecommerce.repository.UserRepository;
import com.slashfmk.ecommerce.security.SecurityConfig;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final SecurityConfig securityConfig;
    private final CartRepository cartRepository;

    public User registerUser(User user) {

        var userExists = this.userRepository.findFirstByEmailAndUsername(user.getEmail(), user.getUsername());

        if (userExists.isPresent())
            throw new IllegalArgumentException("A user with the current email or username exits");

        user.setPassword(this.encodedPassword(user.getPassword()));
        user.setAccountNonLocked(true);
        user.setAccountEnabled(true);
        user.setAccountNonExpired(true);

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

    public List<Cart> getCart(Long id) {

       return this.cartRepository.findCartById(id)
                .orElseThrow(() -> new NoSuchElementException("There is no elements in the cart"));

    }

    /**
     * Util
     * Check if user exists by id
     */
    private User userExists(Long id) {
        return this.userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException("The user doesn't exist"));
    }

}