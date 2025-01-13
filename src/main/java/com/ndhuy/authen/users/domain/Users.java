package com.ndhuy.authen.users.domain;

import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("users")
@Data
@NoArgsConstructor
public class Users {
    @Id
    UUID id;

    String email;

    String phone;
}
