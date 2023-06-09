package com.slashfmk.ecommerce.controller;

import com.slashfmk.ecommerce.jwt.JWTUtil;
import com.slashfmk.ecommerce.model.User;
import com.slashfmk.ecommerce.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final JWTUtil jwtUtil;


    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        var createdUser = this.userService.registerUser(user);

        String jwtToken = jwtUtil.issueToken(createdUser.getEmail(), "ROLE_USER");

        return ResponseEntity.ok()
                .header(HttpHeaders.AUTHORIZATION, jwtToken)
                .build();
    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User userNewInfo) {
        var user = this.userService.updateUser(userNewInfo);
        return ResponseEntity.ok(user);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        var user = this.userService.deleteUser(id);
        return ResponseEntity.ok(user);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        var foundUser = this.userService.getUser(id);
        return ResponseEntity.ok(foundUser);
    }

    public ResponseEntity<List<User>> getUsers() {
        var users = this.userService.getUsers();
        return ResponseEntity.ok(users);
    }

}
