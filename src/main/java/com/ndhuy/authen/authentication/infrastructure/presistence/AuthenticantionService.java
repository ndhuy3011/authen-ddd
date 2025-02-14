package com.ndhuy.authen.authentication.infrastructure.presistence;

import com.ndhuy.authen.authentication.application.domain.AuthenticationCommand;
import com.ndhuy.authen.authentication.application.domain.JwtAuthenticationCommnad;
import com.ndhuy.authen.divice.infrastructure.persistence.RegisterDivices;
import com.ndhuy.authen.users.infrastructure.persistence.AuthenticationJwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
@Slf4j
public class AuthenticantionService {
    @Resource
    RegisterDivices registerDivices;
    @Resource
    AuthenticationJwt authenticationJwt;

    public JwtAuthenticationCommnad authenticationLogin(AuthenticationCommand authenticationCommand) {
        var token = authenticationJwt.getJwt(authenticationCommand.usernameAndPassword());
        var divece = authenticationCommand.divece();
        registerDivices.registerDivice(divece, token.token(), token.uuid());
        return new JwtAuthenticationCommnad(token.token());
    }

}
