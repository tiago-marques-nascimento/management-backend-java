package com.users.management.domain.service;

import java.util.UUID;
import java.util.Optional;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import com.users.management.domain.entity.User;
import com.users.management.domain.repository.BaseRepositoryInterface;
import com.users.management.domain.repository.UserRepositoryInterface;
import com.users.management.domain.validator.BaseValidatorInterface;

public class UserService extends BaseService<User> implements UserServiceInterface  {

    @Autowired
    private UserRepositoryInterface userRepository;

    @Autowired
    private BaseValidatorInterface<User> userValidator;

    @Override
    protected BaseRepositoryInterface<User> getBaseRepository() {
        return this.userRepository;
    }

    @Override
    protected BaseValidatorInterface<User> getBaseValidator() {
        return this.userValidator;
    }

    @Override
    public Optional<User> findByName(final String name) {
        return this.userRepository.findByName(name);
    }

    @Override
    public Optional<User> findByIdWithRoles(final UUID id) {
        return this.userRepository.findByIdWithRoles(id);
    }

    @Override
    public Optional<User> findByNameWithRoles(final String name) {
        return this.userRepository.findByNameWithRoles(name);
    }

    @Override
    public Collection<User> findAllWithRoles() {
        return this.userRepository.findAllWithRoles();
    }
}
