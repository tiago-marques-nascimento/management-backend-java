package com.users.management.domain.factory;

import java.util.List;
import java.util.ArrayList;
import com.users.management.domain.entity.User;
import com.users.management.domain.entity.Role;
import com.users.management.domain.entity.UserRole;

public class UserFactory {

    public static List<User> mockUsers() {

        User user1 = new User();
        user1.setName("Tiago");
        user1.setPassword("122333");
        Role role = new Role();
        role.setName("admin");
        user1.setUserRoles(new ArrayList<UserRole>());
        user1.getUserRoles().add(new UserRole(user1, role));

        User user2 = new User();
        user2.setName("Diogo");
        user2.setPassword("122333");
        user2.setUserRoles(new ArrayList<UserRole>());
        user2.getUserRoles().add(new UserRole(user2, role));

        ArrayList<User> users = new ArrayList<User>();
        users.add(user1);
        users.add(user2);
        return users;
    }

    public static User mockUser() {

        User user = new User();
        user.setName("Tiago");
        user.setPassword("122333");
        Role role = new Role();
        role.setName("admin");
        user.setUserRoles(new ArrayList<UserRole>());
        user.getUserRoles().add(new UserRole(user, role));
        return user;
    }

    public static User mockUserWithId() {

        User user = new User();
        user.create();
        user.setName("Tiago");
        user.setPassword("122333");
        Role role = new Role();
        role.setName("admin");
        user.setUserRoles(new ArrayList<UserRole>());
        user.getUserRoles().add(new UserRole(user, role));
        return user;
    }
}
