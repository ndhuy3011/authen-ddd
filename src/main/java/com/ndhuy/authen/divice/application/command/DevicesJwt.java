package com.ndhuy.authen.divice.application.command;

import com.ndhuy.exceptions.BadRequestException;
import com.ndhuy.exceptions.CommUtils;


import java.util.UUID;
import java.util.stream.Stream;


public record DevicesJwt(String ip, String nameDevices, String jwt, String os, String osVersion, UUID uuid) {
    public DevicesJwt {
        if (Stream.of(ip, nameDevices, jwt, os)
                .anyMatch(CommUtils::validateField) || uuid == null) {
            throw new BadRequestException("error.ERR014");
        }
    }
}
