package com.ndhuy.authen.divice.application.command;

import java.util.UUID;


public record DevicesJwt(String ip, String nameDevices, String jwt, String os, String osVersion, UUID uuid) {


}
