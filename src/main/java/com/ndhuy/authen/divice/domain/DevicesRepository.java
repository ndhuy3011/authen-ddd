package com.ndhuy.authen.divice.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DevicesRepository extends CrudRepository<Devices, String>{
    
}
