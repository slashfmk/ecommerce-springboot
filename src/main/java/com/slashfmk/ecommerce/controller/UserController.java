package com.slashfmk.ecommerce.controller;


import com.slashfmk.ecommerce.model.Cart;
import com.slashfmk.ecommerce.model.Department;
import com.slashfmk.ecommerce.model.Product;
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


    @PostMapping
    public ResponseEntity<?> registerUser(@RequestBody User user) {

        var createdUser = this.userService.registerUser(user);

        return ResponseEntity.ok()
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

    @GetMapping
    public ResponseEntity<List<User>> getUsers() {
        var users = this.userService.getUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/cart/{userid}")
    public ResponseEntity<List<Cart>> getAllCartElements(@PathVariable Long userid) {
       // return ResponseEntity.ok(this.userService.getCart(userid));
        return ResponseEntity.ok(List.of(new Cart(new User("classh", "sl@xx.com", "sss@ss.com", "ppp"), new Product("jelly bean", "al", 12.54,

                new Department("Paste", "dega")))));
    }

}
