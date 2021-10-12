package com.users.management.application.factory;

import java.util.UUID;
import java.util.List;
import java.util.ArrayList;
import com.users.management.application.viewmodel.UserViewModel;
import com.users.management.application.viewmodel.RoleViewModel;

public class UserViewModelFactory {

    public static UserViewModel mockUser() {

        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setName("Tiago");
        userViewModel.setPassword("122333");
        RoleViewModel roleViewModel = new RoleViewModel();
        roleViewModel.setId(UUID.fromString("aa894144-01a3-494e-b636-aa28e2fa9074"));
        roleViewModel.setName("admin");
        userViewModel.setRoles(new ArrayList<RoleViewModel>());
        userViewModel.getRoles().add(roleViewModel);
        return userViewModel;
    }

    public static UserViewModel mockUpdateUser() {

        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setName("Diogo");
        userViewModel.setPassword("122333");
        RoleViewModel roleViewModel = new RoleViewModel();
        roleViewModel.setId(UUID.fromString("aa894144-01a3-494e-b636-aa28e2fa9074"));
        roleViewModel.setName("admin");
        userViewModel.setRoles(new ArrayList<RoleViewModel>());
        userViewModel.getRoles().add(roleViewModel);
        return userViewModel;
    }

    public static UserViewModel mockDeleteUser() {

        UserViewModel userViewModel = new UserViewModel();
        userViewModel.setName("Taynara");
        userViewModel.setPassword("122333");
        RoleViewModel roleViewModel = new RoleViewModel();
        roleViewModel.setId(UUID.fromString("aa894144-01a3-494e-b636-aa28e2fa9074"));
        roleViewModel.setName("admin");
        userViewModel.setRoles(new ArrayList<RoleViewModel>());
        userViewModel.getRoles().add(roleViewModel);
        return userViewModel;
    }
}
