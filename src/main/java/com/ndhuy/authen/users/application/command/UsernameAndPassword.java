package com.ndhuy.authen.users.application.command;

import com.ndhuy.exceptions.CommUtils;

public record UsernameAndPassword(String username, String password) {
    public UsernameAndPassword{
        CommUtils.validateField(username,"error.ERR007");
        CommUtils.validateField(password,"error.ERR013");
    }
}
