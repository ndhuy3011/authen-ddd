package com.ndhuy.authen;

import java.util.UUID;

import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import jakarta.annotation.Resource;
import lombok.experimental.UtilityClass;

@UtilityClass
public class JwtUtils {

    @Resource
    private JwtEncoder jwtEncoder;
    public static String generatorJWT( UUID uuid) {
    

        var claim = JwtClaimsSet.builder()
        .id(uuid.toString())
        .expiresAt(null)
        .issuedAt(null)
        .build();

        var param = JwtEncoderParameters.from( claim);
        return jwtEncoder.encode(param).getTokenValue();
    }

}
