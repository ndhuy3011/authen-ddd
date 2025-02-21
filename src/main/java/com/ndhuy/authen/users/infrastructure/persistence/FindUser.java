package com.ndhuy.authen.users.infrastructure.persistence;

import javax.annotation.Resource;

import com.ndhuy.aspect.UserCase;
import com.ndhuy.authen.users.domain.UserRedis;
import com.ndhuy.authen.users.domain.UserRedisRepository;
import com.ndhuy.exceptions.NotFoundException;

import lombok.extern.slf4j.Slf4j;


@UserCase
@Slf4j
public class FindUser {
    @Resource
    private UserRedisRepository userRepository;

    public UserRedis findUser(String jwt) {
        return userRepository.findById(jwt)
                .orElseThrow(() -> new NotFoundException("error.ERR005"));
    }
}
