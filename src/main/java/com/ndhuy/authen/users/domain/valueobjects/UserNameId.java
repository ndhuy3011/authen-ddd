package com.ndhuy.authen.users.domain;


import com.ndhuy.exceptions.BadRequestException;

public record UserNameId(String username) {
    public UserNameId {
        if (username == null || username.isBlank()) {
            throw new BadRequestException("ERR007");
        }
        if (username.length() < 6) {
            throw new BadRequestException("ERR008");
        }

    }

    public static UserNameId generate(String username) {
        return new UserNameId(username);
    }

}
