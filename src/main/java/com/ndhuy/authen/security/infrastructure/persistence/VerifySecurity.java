package com.ndhuy.authen.security.infrastructure.persistence;

import org.springframework.stereotype.Service;

import com.ndhuy.authen.security.utils.JwtUtils;
import com.ndhuy.authen.users.infrastructure.persistence.FindUser;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class VerifySecurity {
    @Resource
    private FindUser findUser;

    public boolean verify(String authorizationHeader) {
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            var jwt = authorizationHeader.substring(7);
            var user = findUser.findUser(jwt);
            return JwtUtils.validateToken(jwt, user.getId());
        }
        return false;
    }
}
