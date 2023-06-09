package com.slashfmk.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slashfmk.ecommerce.roles.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    @Id
    @Column(
            nullable = false,
            name = "user_id"
    )
    @SequenceGenerator(
            sequenceName = "user_sequence",
            name = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long id;

    @Column(
            nullable = false
    )
    private String name;


    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String email;

    @JsonIgnore
    private String password;

    private List<UserRoles> roles;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    private List<Address> address;

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;

        this.roles = new ArrayList<>();
        this.roles.add(UserRoles.NORMAL);

        this.address = new ArrayList<>();
    }


}
