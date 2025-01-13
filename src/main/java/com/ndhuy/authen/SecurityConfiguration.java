package com.ndhuy.authen;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtDecoders;
import org.springframework.security.web.SecurityFilterChain;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebSecurity
@Slf4j
public class SecurityConfiguration {
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
   
        return http.build();
    }
    @Bean
public JwtDecoder jwtDecoder() {
    return JwtDecoders.fromIssuerLocation("https://idp.example.org");
}
}
