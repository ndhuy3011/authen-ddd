package com.ndhuy.authen.users.domain.valueobjects;

public record Phone(String phone) {
    public Phone {
        if (phone == null || phone.isBlank()) {
            throw new IllegalArgumentException("Phone must not be null or empty");
        }
    }

}
