package com.users.management.domain.repository;

import java.util.UUID;
import java.util.Optional;
import java.util.Collection;
import org.springframework.stereotype.Repository;
import com.users.management.domain.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface UserRepositoryInterface extends BaseRepositoryInterface<User> {

    Optional<User> findByName(String name);

    @Query("SELECT u FROM User u WHERE u.name = :name AND u.id <> :id")
    Optional<User> findOtherUserWithSameName(
        @Param("name") String name,
        @Param("id") UUID id);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.userRoles ur INNER JOIN FETCH ur.role r WHERE u.id = :id")
    Optional<User> findByIdWithRoles(@Param("id") UUID id);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.userRoles ur INNER JOIN FETCH ur.role r WHERE u.name = :name")
    Optional<User> findByNameWithRoles(@Param("name") String name);

    @Query("SELECT u FROM User u INNER JOIN FETCH u.userRoles ur INNER JOIN FETCH ur.role r")
    Collection<User> findAllWithRoles();
}
