package com.ndhuy.authen.authentication.infrastructure.controller;

import javax.annotation.Resource;

import com.ndhuy.authen.authentication.application.domain.CreateUserCommand;
import com.ndhuy.authen.authentication.application.domain.InfoUserCommand;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ndhuy.authen.authentication.application.domain.AuthenticationCommand;
import com.ndhuy.authen.authentication.application.domain.JwtAuthenticationCommnad;
import com.ndhuy.authen.authentication.infrastructure.presistence.AuthenticantionService;
import com.ndhuy.authen.authentication.infrastructure.presistence.VerifySecurity;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/authentication")
public class AuthenticationController {

    @Resource
    AuthenticantionService authenticantionService;

    @Resource
    VerifySecurity verifySecurity;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthenticationCommnad> postLogin(@RequestBody AuthenticationCommand command) {
        return ResponseEntity.ok(authenticantionService.authenticationLogin(command));
    }
    @PostMapping("/register")
    public ResponseEntity<InfoUserCommand> postRegister(@RequestBody CreateUserCommand command){
        return ResponseEntity.ok(authenticantionService.authenticationRegister(command));
    }

    @GetMapping("/check-auth")
    public ResponseEntity<HttpStatusCode> checkAuth(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");
        if (verifySecurity.verify(authHeader)) {
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
