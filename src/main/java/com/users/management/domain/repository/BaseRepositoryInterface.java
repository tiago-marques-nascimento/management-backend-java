package com.users.management.domain.repository;

import java.util.UUID;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.users.management.domain.entity.BaseEntity;

@Repository
public interface BaseRepositoryInterface<T extends BaseEntity> extends CrudRepository<T, UUID> {
}
