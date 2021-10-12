package com.users.management.application.viewmodel;

import java.util.UUID;

public class RoleViewModel {

    private UUID id;
    private String name;

    public UUID getId() {
        return this.id;
    }
    public String getName() {
        return this.name;
    }

    public void setId(final UUID id) {
        this.id = id;
    }
    public void setName(final String name) {
        this.name = name;
    }
}
