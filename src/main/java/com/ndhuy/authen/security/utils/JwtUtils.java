package com.ndhuy.authen.security.utils;

import java.time.Duration;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import jakarta.annotation.Resource;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtUtils {

    @Resource
    private JwtEncoder jwtEncoder;

    @Resource
    private JwtDecoder jwtDecoder;

    public static String generatorJWT(UUID uuid) {

        var claim = JwtClaimsSet.builder()
                .id(uuid.toString())
                .claim("device", "mobile")
                .expiresAt(Instant.now().plus(Duration.ofHours(1L)))
                .issuedAt(Instant.now())
                .build();

        var param = JwtEncoderParameters.from(claim);
        return jwtEncoder.encode(param).getTokenValue();
    }

    public static Map<String, Object> parseJWT(String token) {
        return jwtDecoder.decode(token).getClaims();
    }

    public static UUID getUUIDFromJWT(String token) {
        return UUID.fromString((String) parseJWT(token).get("jti"));
    }

    public static String getDeviceFromJWT(String token) {
        return (String) parseJWT(token).get("device");
    }

    public static Instant getIssuedAtFromJWT(String token) {
        return (Instant) parseJWT(token).get("iat");
    }

    public static Instant getExpiresAtFromJWT(String token) {
        return (Instant) parseJWT(token).get("exp");
    }

    public static Boolean isExpired(String token) {
        return getExpiresAtFromJWT(token).isBefore(Instant.now());
    }

    public static Boolean validateToken(String token, UUID uuid) {
        return uuid.equals(getUUIDFromJWT(token)) && isNotExpired(token);
    }

    public static Boolean isNotExpired(String token) {
        return !isExpired(token);
    }

}
