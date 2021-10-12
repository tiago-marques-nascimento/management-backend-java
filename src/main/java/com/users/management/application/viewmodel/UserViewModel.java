package com.users.management.application.viewmodel;

import java.util.UUID;
import java.util.List;

public class UserViewModel {

    private UUID id;
    private String name;
    private String password;
    private List<RoleViewModel> roles;

    public UUID getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
    public List<RoleViewModel> getRoles() {
        return this.roles;
    }

    public void setId(final UUID id) {
        this.id = id;
    }
    public void setName(final String name) {
        this.name = name;
    }
    public void setPassword(final String password) {
        this.password = password;
    }
    public void setRoles(final List<RoleViewModel> roles) {
        this.roles = roles;
    }
}
