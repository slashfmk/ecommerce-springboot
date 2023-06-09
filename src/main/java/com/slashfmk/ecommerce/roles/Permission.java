package com.slashfmk.ecommerce.roles;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_DELETE("admin:delete"),
    ADMIN_CREATE("admin:create"),

    NORMAL_READ("normal:read"),
    NORMAL_UPDATE("normal:update"),
    NORMAL_DELETE("normal:delete"),
    NORMAL_CREATE("normal:create");

    @Getter
    private final String permission;
}
