package com.ndhuy.authen.users.domain;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Users, UUID> {

}