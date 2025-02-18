package com.ndhuy.authen.users.infrastructure.persistence;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
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
        var user = Optional.ofNullable(userCommunicateGrpc.authenticate(
                        usernameAndPassword.username(), usernameAndPassword.password()))
                .orElseThrow(() -> new BadCredentialsException("Authen"));
        var uuid = UUID.fromString(user.getUuid());
        return new JwtCommand(generatorJWT(uuid), uuid);
    }

    public String generatorJWT(UUID uuid) {
        var now = Instant.now();
        var claims = JwtClaimsSet.builder()
                .id(uuid.toString())
                .claim("device", "mobile")
                .issuedAt(now)
                .expiresAt(now.plus(Duration.ofHours(1)))
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();
    }
    public  Map<String, Object> parseJWT(String token) {
        return jwtDecoder.decode(token).getClaims();
    }

    public UUID getUUIDFromJWT(String token) {
        return Optional.ofNullable(parseJWT(token).get("jti"))
                .map(Object::toString)
                .map(UUID::fromString)
                .orElse(null);
    }

    public String getDeviceFromJWT(String token) {
        return Optional.ofNullable(parseJWT(token).get("device"))
                .map(Object::toString)
                .orElse(null);
    }

    public  Instant getIssuedAtFromJWT(String token) {
        return (Instant) parseJWT(token).get("iat");
    }

    public  Instant getExpiresAtFromJWT(String token) {
        return (Instant) parseJWT(token).get("exp");
    }

    public Boolean isExpired(String token) {
        return Optional.ofNullable(getExpiresAtFromJWT(token))
                .map(exp -> exp.isBefore(Instant.now()))
                .orElse(true);
    }
    public Boolean validateToken(String token, UUID uuid) {
        return Optional.ofNullable(token)
                .filter(this::isNotExpired)
                .map(this::getUUIDFromJWT)
                .filter(uuid::equals)
                .isPresent();
    }

    public  Boolean isNotExpired(String token) {
        return !isExpired(token);
    }

}
