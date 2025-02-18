package com.ndhuy.authen.divice.application.command;

import java.util.Objects;
import java.util.UUID;


public record DevicesJwt(String ip, String nameDevices, String jwt, String os, String osVersion, UUID uuid) {
    public DevicesJwt {
        ip = Objects.requireNonNullElse(ip, "UNKNOWN");
        nameDevices = Objects.requireNonNullElse(nameDevices, "UNKNOWN_DEVICE");
        jwt = Objects.requireNonNullElse(jwt, "INVALID_JWT");
        os = Objects.requireNonNullElse(os, "UNKNOWN_OS");
        osVersion = Objects.requireNonNullElse(osVersion, "UNKNOWN_VERSION");
        uuid = Objects.requireNonNullElse(uuid, UUID.randomUUID());
    }

}
