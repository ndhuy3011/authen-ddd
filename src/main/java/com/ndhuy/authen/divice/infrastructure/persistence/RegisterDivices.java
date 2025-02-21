package com.ndhuy.authen.divice.infrastructure.persistence;

import com.ndhuy.authen.divice.application.command.DevicesJwt;
import com.ndhuy.authen.divice.domain.Devices;
import com.ndhuy.authen.divice.domain.DevicesRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.UUID;

@Service
@Slf4j
public class RegisterDivices {

    @Resource
    DevicesRepository devicesRepository;

    public void registerDivice(DevicesJwt devices, String jwt, UUID uuid
    ){
    devicesRepository.save( Devices.builder()
            .jwt(jwt)
            .id(uuid)
            .deviceId(devices.nameDevices())
            .os(devices.os())
            .osVersion(devices.osVersion())
            .build());
    }
}
