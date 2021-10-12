package com.users.management.domain.factory;

import java.util.List;
import java.util.ArrayList;
import com.users.management.domain.entity.Role;

public class RoleFactory {

    public static List<Role> mockRoles() {

        Role role1 = new Role();
        role1.setName("Tiago");

        Role role2 = new Role();
        role2.setName("Diogo");

        ArrayList<Role> roles = new ArrayList<Role>();
        roles.add(role1);
        roles.add(role2);
        return roles;
    }
}
