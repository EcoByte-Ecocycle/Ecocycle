package com.ecobyte.ecocycle.dto.response;

import com.ecobyte.ecocycle.domain.user.Role;
import java.util.Locale;
import lombok.Getter;

@Getter
public class LoginResponse {

    private final String role;
    private final String accessToken;

    public LoginResponse(final Role role, final String accessToken) {
        this.role = role.name().toLowerCase(Locale.ROOT);
        this.accessToken = accessToken;
    }
}
