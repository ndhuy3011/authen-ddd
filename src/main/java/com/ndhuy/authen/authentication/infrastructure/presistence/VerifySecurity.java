package com.ndhuy.authen.authentication.infrastructure.presistence;

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
        return verifyUser(jwt) && verifyDevice(jwt);

    }

    private boolean verifyUser(String jwt){
        return false;
    }
    private boolean verifyDevice(String jwt){
        return false;
    }
}
