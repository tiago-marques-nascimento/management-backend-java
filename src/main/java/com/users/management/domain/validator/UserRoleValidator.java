package com.users.management.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import com.users.management.domain.entity.UserRole;
import com.users.management.domain.repository.UserRoleRepositoryInterface;

public class UserRoleValidator extends BaseValidator<UserRole> {

    @Autowired
    private UserRoleRepositoryInterface userRoleRepository;

    public void validateCreate(final UserRole entity) {
    }

    public void validateUpdate(final UserRole entity) {
    }

    public void validateDelete(final UserRole entity) {
    }
}
