package com.ndhuy.authen.users.domain;

import org.springframework.data.repository.CrudRepository;

public interface UserRedisRepository extends CrudRepository<UsersRedis, String> {

}