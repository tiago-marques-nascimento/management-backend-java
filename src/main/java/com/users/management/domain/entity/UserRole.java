package com.users.management.domain.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.OneToOne;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;

@Entity
@Table(name = "User_Role")
public class UserRole extends BaseEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private Role role;

    public UserRole() {
    }

    public UserRole(final User user, final Role role) {
        this.user = user;
        this.role = role;
    }

    public User getUser() {
        return this.user;
    }
    public Role getRole() {
        return this.role;
    }

    public void setUser(final User user) {
        this.user = user;
    }
    public void setRole(final Role role) {
        this.role = role;
    }
}
