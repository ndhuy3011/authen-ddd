package com.ndhuy.authen.authentication.application.domain;

import com.ndhuy.exceptions.BadRequestException;
import com.ndhuy.exceptions.CommUtils;

import java.util.Objects;

public record InfoUserCommand(String id) {
   public InfoUserCommand{
       CommUtils.validateField(id,"error.ERR011");
    }
}
