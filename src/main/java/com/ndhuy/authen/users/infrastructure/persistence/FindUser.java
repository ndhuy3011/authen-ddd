package com.ndhuy.authen.users.infrastructure.persistence;

import javax.annotation.Resource;

import jakarta.persistence.EntityNotFoundException;
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
        return userRepository.findById(jwt)
                .orElseThrow(() -> new EntityNotFoundException("User not found for token: " + jwt));
    }
}
