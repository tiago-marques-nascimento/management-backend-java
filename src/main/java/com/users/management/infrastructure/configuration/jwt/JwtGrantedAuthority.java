package com.users.management.infrastructure.configuration.jwt;

import org.springframework.security.core.GrantedAuthority;

public class JwtGrantedAuthority implements GrantedAuthority {

    private final String grantedAuthority;

    public JwtGrantedAuthority(final String grantedAuthority) {
        this.grantedAuthority = grantedAuthority;
    }

    @Override
    public String getAuthority() {
        return this.grantedAuthority;
    }
}
