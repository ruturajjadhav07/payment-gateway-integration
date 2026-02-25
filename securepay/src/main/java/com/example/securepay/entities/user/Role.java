package com.example.securepay.entities.user;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Role {
    ADMIN, USER;

    @JsonValue
    public String toValue() {
        return this.name();
    }

    @JsonCreator
    public static Role fromValue(String value) {
        if (value == null) return null;
        return Role.valueOf(value.toUpperCase()); // Converts lowercase input to uppercase
    }
}