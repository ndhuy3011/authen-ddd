package com.ndhuy.authen.users.infrastructure.persistence;

import com.ndhuy.authen.JwtUtils;
import com.ndhuy.authen.users.application.command.JwtCommand;
import com.ndhuy.authen.users.application.command.UsernameAndPassword;
import com.ndhuy.authen.users.infrastructure.communicate.UserCommunicateGrpc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Slf4j
public class AuthenticationJwt {

    @Resource
    UserCommunicateGrpc userCommunicateGrpc;

    public JwtCommand getJwt(UsernameAndPassword usernameAndPassword) {

        var user  = userCommunicateGrpc.authenticate(usernameAndPassword.username(),usernameAndPassword.password());
        var uuid = UUID.fromString(user.getUuid());
        var token = JwtUtils.generatorJWT(uuid);
        return new JwtCommand(token,uuid);
    }


}
