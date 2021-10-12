package com.users.management.application.facade;

import java.util.UUID;
import java.util.List;
import com.users.management.application.viewmodel.UserViewModel;

public interface UserFacadeInterface {

    UserViewModel findByName(String name);
    List<UserViewModel> findAll();
    void save(UserViewModel user);
    void delete(UUID id);
}
