package com.ndhuy.authen.users.domain;

import java.util.UUID;

import com.ndhuy.authen.users.domain.valueobjects.Email;
import com.ndhuy.authen.users.domain.valueobjects.Phone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;
import lombok.NoArgsConstructor;

@RedisHash("users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRedis {

    @Id
    String jwt;

    UUID id;

    Email email;

    Phone phone;
}
