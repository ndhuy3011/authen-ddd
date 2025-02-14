package com.ndhuy.authen.security.infrastructure.persistence;

import com.ndhuy.authen.users.infrastructure.persistence.UserJwt;
import org.springframework.stereotype.Service;

import com.ndhuy.authen.users.infrastructure.persistence.FindUser;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VerifySecurity {
    @Resource
    private FindUser findUser;
    @Resource
    private UserJwt userJwt;

    public boolean verify(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var jwt = authorizationHeader.substring(7);
            var user = findUser.findUser(jwt);
            return userJwt.validateToken(jwt, user.getId());
        }
        return false;
    }
}
