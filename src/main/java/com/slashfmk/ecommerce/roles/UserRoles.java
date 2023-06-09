package com.slashfmk.ecommerce.roles;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
public enum UserRoles {

    ADMIN(
            Set.of(
                    Permission.ADMIN_UPDATE,
                    Permission.ADMIN_DELETE,
                    Permission.ADMIN_CREATE,
                    Permission.ADMIN_READ,
                    Permission.NORMAL_CREATE,
                    Permission.NORMAL_UPDATE,
                    Permission.NORMAL_DELETE,
                    Permission.NORMAL_READ
            )
    ),
    NORMAL(
            Set.of(
                    Permission.NORMAL_READ
            )
    ),
    MANAGER(Collections.emptySet());

    @Getter
    private final Set<Permission> permission;

    public List<SimpleGrantedAuthority> getAuthorities () {

        var authorities = getPermission()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList();

        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));

        return authorities;
    }

}
