package com.slashfmk.ecommerce.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.slashfmk.ecommerce.roles.Role;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class User implements UserDetails {

    @Id
    @SequenceGenerator(
            sequenceName = "user_sequence",
            name = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.AUTO,
            generator = "user_sequence"
    )
    private Long UserId;

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

    @OneToMany(
            cascade = CascadeType.ALL,
            mappedBy = "user",
            orphanRemoval = true,
            fetch = FetchType.EAGER
    )
    @JsonManagedReference
    private List<Address> address = new ArrayList<>();


    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL
    )
    private List<Cart> products = new ArrayList<>();

    public User(String name, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.accountNonExpired = true;
        this.accountEnabled = true;
        this.accountNonLocked = true;
    }

//    public void addProduct(Product product) {
//        this.products.add(new Cart(this, product));
//    }

    public void addAddress(Address address) {
        if (!this.address.contains(address)) {
            this.address.add(address);
            address.setUser(this);
        }
    }

    public void removeAddress(Address address) {
        address.setUser(null);
        this.address.remove(address);
    }

    @Enumerated(EnumType.STRING)
    private Role roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.name()));
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
