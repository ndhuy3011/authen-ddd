package com.ndhuy.authen.authentication.application.domain;

import com.ndhuy.exceptions.BadRequestException;
import com.ndhuy.exceptions.CommUtils;
import lombok.Builder;

@Builder
public record CreateUserCommand(String username, String password, String email, String phone, String fullName) {
    public CreateUserCommand {
        CommUtils.validateField(username,"error.ERR007");
        CommUtils.validateField(password,"error.ERR013");
        CommUtils.validateField(email,"error.ERR010");
        CommUtils.validateField(phone,"error.ERR015");
        CommUtils.validateField(fullName,"error.ERR009");
    }

    
}
