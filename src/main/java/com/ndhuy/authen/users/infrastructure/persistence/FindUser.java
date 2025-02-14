package com.ndhuy.authen.users.infrastructure.persistence;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.ndhuy.authen.users.domain.UserRepository;
import com.ndhuy.authen.users.domain.Users;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FindUser {
    @Resource
    private UserRepository userRepository;


    public Users findUser(String jwt) {
        var user = userRepository.findById(jwt);
        log.info("User: {}", user);
        return user.orElse(null);
    }
   
}
