package com.users.management.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.users.management.domain.entity.UserRole;
import com.users.management.domain.repository.BaseRepositoryInterface;
import com.users.management.domain.repository.UserRoleRepositoryInterface;
import com.users.management.domain.validator.BaseValidatorInterface;

public class UserRoleService extends BaseService<UserRole> implements UserRoleServiceInterface {

    @Autowired
    private UserRoleRepositoryInterface userRoleRepository;

    @Autowired
    private BaseValidatorInterface<UserRole> userRoleValidator;

    @Override
    protected BaseRepositoryInterface<UserRole> getBaseRepository() {
        return this.userRoleRepository;
    }

    @Override
    protected BaseValidatorInterface<UserRole> getBaseValidator() {
        return this.userRoleValidator;
    }
}
