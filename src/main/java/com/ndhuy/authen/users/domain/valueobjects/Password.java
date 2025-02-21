package com.ndhuy.user.users.domain.valueobjects;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.BCryptVersion;

public record Password(String value) {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(BCryptVersion.$2Y);

    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password is required");
        }
        if (value.length() < 6) {
            throw new IllegalArgumentException("Password must be at least 6 characters");
        }
        // Check if the password needs to be upgraded
        try {
            encoder.upgradeEncoding(value);
        } catch (Exception e) {
            value = encoder.encode(value);
        }

    }

    /**
     * 
     * @param value
     * @return Password
     */
    public static Password from(String value) {
        return new Password(value);
    }

    /**
     * 
     * @param rawPassword
     * @return boolean
     */
    public boolean match(String rawPassword) {
        return encoder.matches(rawPassword, value);
    }

}
