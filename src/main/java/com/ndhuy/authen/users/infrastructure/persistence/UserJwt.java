package com.ndhuy.authen.users.infrastructure.persistence;

import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ndhuy.authen.security.utils.JwtUtils;
import com.ndhuy.authen.users.application.command.JwtCommand;
import com.ndhuy.authen.users.application.command.UsernameAndPassword;
import com.ndhuy.authen.users.infrastructure.communicate.UserCommunicateGrpc;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserJwt {

    @Resource
    UserCommunicateGrpc userCommunicateGrpc;

    public JwtCommand getJwt(UsernameAndPassword usernameAndPassword) {

        var user  = userCommunicateGrpc.authenticate(usernameAndPassword.username(),usernameAndPassword.password());
        var uuid = UUID.fromString(user.getUuid());
        var token = JwtUtils.generatorJWT(uuid);
        return new JwtCommand(token,uuid);
    }


}
