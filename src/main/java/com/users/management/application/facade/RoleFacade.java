package com.users.management.application.facade;

import java.util.List;
import java.util.stream.Collectors;
import javax.inject.Inject;
import org.modelmapper.ModelMapper;
import com.users.management.application.viewmodel.RoleViewModel;
import com.users.management.domain.service.RoleServiceInterface;

public class RoleFacade implements RoleFacadeInterface {

    @Inject
    private RoleServiceInterface roleService;

    @Inject
    private ModelMapper modelMapper;

    public List<RoleViewModel> findAll() {

        return this.roleService
            .findAll()
            .stream()
            .map(role -> modelMapper.map(role, RoleViewModel.class))
            .collect(Collectors.toList());
    }
}
