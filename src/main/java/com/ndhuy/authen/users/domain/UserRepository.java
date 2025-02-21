package com.ndhuy.authen.users.domain;

import com.ndhuy.authen.users.domain.valueobjects.UserNameId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository  extends JpaRepository<User, UserNameId> {
   Optional<User> findByUuid(UUID uuid);
}
