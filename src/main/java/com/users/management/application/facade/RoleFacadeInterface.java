package com.users.management.application.facade;

import java.util.List;
import com.users.management.application.viewmodel.RoleViewModel;

public interface RoleFacadeInterface {

    List<RoleViewModel> findAll();
}
