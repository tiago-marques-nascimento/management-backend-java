package com.users.management.domain.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;

@Entity
@Table(name = "Role")
public class Role extends BaseEntity {

    @Column()
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "role")
    private List<UserRole> userRoles;

    public String getName() {
        return this.name;
    }
    public List<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setName(final String name) {
        this.name = name;
    }
    public void setUserRoles(final List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
