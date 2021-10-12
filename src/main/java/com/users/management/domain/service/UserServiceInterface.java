package com.users.management.domain.service;

import java.util.UUID;
import java.util.Optional;
import java.util.Collection;
import com.users.management.domain.entity.User;

public interface UserServiceInterface extends BaseServiceInterface<User> {

    Optional<User> findByName(String name);
    Optional<User> findByIdWithRoles(UUID id);
    Optional<User> findByNameWithRoles(String name);
    Collection<User> findAllWithRoles();
}
