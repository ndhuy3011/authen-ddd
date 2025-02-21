package com.ndhuy.user.users.domain;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, UserNameId> {
   Optional<User> findByUuid(UUID uuid);
}
