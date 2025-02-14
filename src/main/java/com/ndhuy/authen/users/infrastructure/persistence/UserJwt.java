package com.ndhuy.authen.users.infrastructure.persistence;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import com.ndhuy.authen.users.application.command.JwtCommand;
import com.ndhuy.authen.users.application.command.UsernameAndPassword;
import com.ndhuy.authen.users.infrastructure.communicate.UserCommunicateGrpc;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserJwt {

    @Resource
    UserCommunicateGrpc userCommunicateGrpc;
    @Resource
    JwtEncoder jwtEncoder;

    @Resource
    JwtDecoder jwtDecoder;
    public JwtCommand getJwt(UsernameAndPassword usernameAndPassword) {

        var user  = userCommunicateGrpc.authenticate(usernameAndPassword.username(),usernameAndPassword.password());
        if(Objects.isNull(user)){
             throw new BadCredentialsException("Authen");
        }
        var uuid = UUID.fromString(user.getUuid());
        var token = generatorJWT(uuid);
        return new JwtCommand(token,uuid);
    }

    public String generatorJWT(UUID uuid) {

        var claim = JwtClaimsSet.builder()
                .id(uuid.toString())
                .claim("device", "mobile")
                .expiresAt(Instant.now().plus(Duration.ofHours(1L)))
                .issuedAt(Instant.now())
                .build();

        var param = JwtEncoderParameters.from(claim);
        return jwtEncoder.encode(param).getTokenValue();
    }
    public  Map<String, Object> parseJWT(String token) {
        return jwtDecoder.decode(token).getClaims();
    }

    public  UUID getUUIDFromJWT(String token) {
        return UUID.fromString((String) parseJWT(token).get("jti"));
    }

    public  String getDeviceFromJWT(String token) {
        return (String) parseJWT(token).get("device");
    }

    public  Instant getIssuedAtFromJWT(String token) {
        return (Instant) parseJWT(token).get("iat");
    }

    public  Instant getExpiresAtFromJWT(String token) {
        return (Instant) parseJWT(token).get("exp");
    }

    public  Boolean isExpired(String token) {
        return getExpiresAtFromJWT(token).isBefore(Instant.now());
    }

    public  Boolean validateToken(String token, UUID uuid) {
        return uuid.equals(getUUIDFromJWT(token)) && isNotExpired(token);
    }

    public  Boolean isNotExpired(String token) {
        return !isExpired(token);
    }

}
