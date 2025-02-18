package com.ndhuy.authen.authentication.infrastructure.presistence;

import javax.annotation.Resource;

import com.ndhuy.authen.authentication.application.domain.CreateUserCommand;
import com.ndhuy.authen.authentication.application.domain.InfoUserCommand;
import com.ndhuy.authen.users.infrastructure.communicate.UserCommunicateGrpc;
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

    @Resource
    UserCommunicateGrpc userCommunicateGrpc;

    public JwtAuthenticationCommnad authenticationLogin(AuthenticationCommand authenticationCommand) {
        var token = userJwt.registerUserJwt(authenticationCommand.usernameAndPassword());
        registerDivices.registerDivice(authenticationCommand.divece(), token.token(), token.uuid());
        return new JwtAuthenticationCommnad(token.token());
    }

    public InfoUserCommand authenticationRegister(CreateUserCommand userCommand){
        var user =userCommunicateGrpc.register(userCommand);
        return new InfoUserCommand(user.getUuid());

    }

}
