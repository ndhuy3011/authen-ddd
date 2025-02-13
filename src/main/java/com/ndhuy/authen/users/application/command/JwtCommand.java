package com.ndhuy.authen.users.application.command;

import java.util.UUID;

public record JwtCommand(String token, UUID uuid) {
}
