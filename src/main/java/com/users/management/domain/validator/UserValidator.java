package com.users.management.domain.validator;

import org.springframework.beans.factory.annotation.Autowired;
import com.users.management.domain.entity.User;
import com.users.management.domain.repository.UserRepositoryInterface;
import com.users.management.infrastructure.exception.BusinessException;

public class UserValidator extends BaseValidator<User> {

    @Autowired
    private UserRepositoryInterface userRepository;

    public void validateCreate(final User entity) {

        //We could use a unique constraint on the JPA mapping, however, this demonstrates how a simple validation works.
        if (this.userRepository.findByName(entity.getName()).isPresent()) {
            throw new BusinessException("Error creating user: The name already exists");
        }
        if (entity.getUserRoles() == null || entity.getUserRoles().size() == 0) {
            throw new BusinessException("Error creating user: It must have at least one role");
        }
    }

    public void validateUpdate(final User entity) {

        if (this.userRepository.findOtherUserWithSameName(entity.getName(), entity.getId()).isPresent()) {
            throw new BusinessException("Error creating user: The name already exists");
        }
        if (entity.getUserRoles() == null || entity.getUserRoles().size() == 0) {
            throw new BusinessException("Error creating user: It must have at least one role");
        }
    }

    public void validateDelete(final User entity) {
    }
}
