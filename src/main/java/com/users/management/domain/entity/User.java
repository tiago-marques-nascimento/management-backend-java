package com.users.management.domain.entity;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.OneToMany;
import javax.persistence.FetchType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Table(name = "User")
public class User extends BaseEntity {

    @Column()
    private String name;

    @Column()
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRole> userRoles;

    public String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
    public List<UserRole> getUserRoles() {
        return this.userRoles;
    }

    public void setName(final String name) {
        this.name = name;
    }
    public void setPassword(final String password) {
        this.password = new BCryptPasswordEncoder().encode(password);
    }
    public void setUserRoles(final List<UserRole> userRoles) {
        this.userRoles = userRoles;
    }
}
