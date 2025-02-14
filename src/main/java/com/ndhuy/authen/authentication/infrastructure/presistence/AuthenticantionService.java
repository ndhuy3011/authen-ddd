package com.ndhuy.authen.authentication.infrastructure.presistence;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ndhuy.authen.authentication.application.domain.AuthenticationCommand;
import com.ndhuy.authen.authentication.application.domain.JwtAuthenticationCommnad;
import com.ndhuy.authen.divice.infrastructure.persistence.RegisterDivices;
import com.ndhuy.authen.users.infrastructure.persistence.UserJwt;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthenticantionService {
    @Resource
    RegisterDivices registerDivices;
    @Resource
    UserJwt userJwt;

    public JwtAuthenticationCommnad authenticationLogin(AuthenticationCommand authenticationCommand) {
        var token = userJwt.getJwt(authenticationCommand.usernameAndPassword());
        var divece = authenticationCommand.divece();
        registerDivices.registerDivice(divece, token.token(), token.uuid());
        return new JwtAuthenticationCommnad(token.token());
    }

}
