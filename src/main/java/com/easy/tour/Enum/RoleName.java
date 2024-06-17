package com.easy.tour.Enum;

import lombok.Getter;

@Getter
public enum RoleName {
    USER((byte) 1),
    MANAGER((byte) 2),
    ADMIN((byte) 3);

    public static final String STRING_USER = "USER";
    public static final String STRING_MANAGER = "MANAGER";
    public static final String STRING_ADMIN = "ADMIN";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_MANAGER = "ROLE_MANAGER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";

    private final byte value;

    RoleName(byte value) {
        this.value = value;
    }
}