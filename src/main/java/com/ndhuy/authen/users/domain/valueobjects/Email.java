package com.ndhuy.user.profiles.domain.valueobjects;

public record Email(String value) {
    public Email {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Email must not be null or blank");
        }
        if(!value.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")) {
            throw new IllegalArgumentException("Email is invalid");
        }
    }

}
