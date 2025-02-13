package com.ndhuy.authen.users.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("devices")
@Data
@NoArgsConstructor
public class Devices {
    @Id
    String jwt;

    String deviceId;

    String os;

    String  osVersion;

    String userAgent;

    UUID id;
}
