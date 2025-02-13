package com.ndhuy.authen.authentication.application.domain;

import com.ndhuy.authen.divice.application.command.DevicesJwt;
import com.ndhuy.authen.users.application.command.UsernameAndPassword;

public record AuthenticationCommand(DevicesJwt divece, UsernameAndPassword usernameAndPassword) {
}
