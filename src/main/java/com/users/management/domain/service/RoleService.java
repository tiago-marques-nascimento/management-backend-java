package com.users.management.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.users.management.domain.entity.Role;
import com.users.management.domain.repository.BaseRepositoryInterface;
import com.users.management.domain.repository.RoleRepositoryInterface;
import com.users.management.domain.validator.BaseValidatorInterface;

public class RoleService extends BaseService<Role> implements RoleServiceInterface {

    @Autowired
    private RoleRepositoryInterface roleRepository;

    @Autowired
    private BaseValidatorInterface<Role> roleValidator;

    @Override
    protected BaseRepositoryInterface<Role> getBaseRepository() {
        return this.roleRepository;
    }

    @Override
    protected BaseValidatorInterface<Role> getBaseValidator() {
        return this.roleValidator;
    }
}
