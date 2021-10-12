package com.users.management.domain.repository;

import java.util.UUID;
import java.util.Optional;
import org.springframework.stereotype.Repository;
import com.users.management.domain.entity.Role;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface RoleRepositoryInterface extends BaseRepositoryInterface<Role> {

    Optional<Role> findByName(String name);

    @Query("SELECT r FROM Role r WHERE r.name = :name AND r.id <> :id")
    Optional<Role> findOtherRoleWithSameName(
        @Param("name") String name,
        @Param("id") UUID id);
}
