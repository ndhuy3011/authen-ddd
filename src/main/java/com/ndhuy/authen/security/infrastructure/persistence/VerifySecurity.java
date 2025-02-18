package com.ndhuy.authen.security.infrastructure.persistence;

import com.ndhuy.authen.users.infrastructure.persistence.UserJwt;
import org.springframework.stereotype.Service;

import com.ndhuy.authen.users.infrastructure.persistence.FindUser;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

@Service
@Slf4j
public class VerifySecurity {
    @Resource
    private FindUser findUser;
    @Resource
    private UserJwt userJwt;

    public boolean verify(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            return false;
        }

        var jwt = authorizationHeader.substring(7);
        return Optional.ofNullable(findUser.findUser(jwt))
                .map(user -> userJwt.validateToken(jwt, user.getId()))
                .orElse(false);
    }

}
