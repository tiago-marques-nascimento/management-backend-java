package com.users.management.infrastructure.configuration.jwt;

import java.util.Collection;
import java.util.stream.Collectors;
import com.users.management.domain.entity.User;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUserDetails implements UserDetails {

    private User user;

    public JwtUserDetails(final User user) {
        this.user = user;
    }

    public Collection<JwtGrantedAuthority> getAuthorities() {
        return this.user.getUserRoles().stream()
            .map(userRole -> new JwtGrantedAuthority(userRole.getRole().getName()))
            .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return "{bcrypt}" + this.user.getPassword();
    }

    @Override
    public String getUsername() {
        return this.user.getName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
