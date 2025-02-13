package com.ndhuy.authen.authentication.infrastructure.controller;

import com.ndhuy.authen.authentication.application.domain.AuthenticationCommand;
import com.ndhuy.authen.authentication.application.domain.JwtAuthenticationCommnad;
import com.ndhuy.authen.authentication.infrastructure.presistence.AuthenticantionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Resource
    AuthenticantionService authenticantionService;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationCommnad> postLogin(@RequestBody AuthenticationCommand command) {
        return ResponseEntity.ok(authenticantionService.authenticationLogin(command));
    }
}
