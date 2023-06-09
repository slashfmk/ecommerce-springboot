package com.slashfmk.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.slashfmk.ecommerce.roles.UserRoles;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class User implements UserDetails {

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


    @Column(
            nullable = false,
            unique = true
    )
    private String username;

    @Column(
            nullable = false,
            unique = true
    )
    private String email;

    @Column(nullable = false)
    private String password;

    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean accountEnabled;

//    private List<UserRoles> roles;

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
        this.accountNonExpired = true;
        this.accountEnabled = true;
        this.accountNonLocked = true;

//        this.roles = new ArrayList<>();
//        this.roles.add(UserRoles.NORMAL);

        this.address = new ArrayList<>();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.accountEnabled;
    }
}
