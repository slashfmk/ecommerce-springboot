package com.slashfmk.ecommerce.service;


import com.slashfmk.ecommerce.model.User;
import com.slashfmk.ecommerce.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User registerUser(User user) {

        var userExists = this.userRepository.findFirstByEmailAndUsername(user.getEmail(), user.getUsername());

        if (userExists.isPresent())
            throw new IllegalArgumentException("A user with the current email or username exits");

        this.userRepository.save(user);

        return user;
    }


    public User updateUser(User userNewInfo) {

        var userExists = this.userRepository.findFirstByEmailAndUsername(userNewInfo.getEmail(), userNewInfo.getUsername());

        if (userExists.isEmpty()) throw new IllegalStateException("There is no such user to update");

        this.userRepository.save(userNewInfo);

        return userNewInfo;
    }


    public User getUser(Long id) {

        return this.userExists(id);
    }


    public User deleteUser(Long id) {

        return this.userExists(id);
    }


    public List<User> getUsers() {
        return this.userRepository.findAll();
    }

    private User userExists(Long id) {

        var userExist = this.userRepository.findById(id);

        if (userExist.isEmpty()) throw new IllegalStateException("user with " + id + " doesn't exist");

        return userExist.get();
    }

}