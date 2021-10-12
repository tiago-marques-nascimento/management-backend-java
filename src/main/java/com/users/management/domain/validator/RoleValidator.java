package com.users.management.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import com.users.management.domain.entity.Role;
import com.users.management.domain.repository.RoleRepositoryInterface;
import com.users.management.infrastructure.exception.BusinessException;

public class RoleValidator extends BaseValidator<Role> {

    @Autowired
    private RoleRepositoryInterface roleRepository;

    public void validateCreate(final Role entity) {

        //We could use a unique constraint on the JPA mapping, however, this demonstrates how a simple validation works.
        if (this.roleRepository.findByName(entity.getName()).isPresent()) {
            throw new BusinessException("Error creating role: The name already exists");
        }
    }

    public void validateUpdate(final Role entity) {

        if (this.roleRepository.findOtherRoleWithSameName(entity.getName(), entity.getId()).isPresent()) {
            throw new BusinessException("Error creating role: The name already exists");
        }
    }

    public void validateDelete(final Role entity) {
    }
}
