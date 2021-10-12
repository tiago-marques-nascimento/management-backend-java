package com.users.management.domain.service;

import java.util.UUID;
import java.util.List;
import com.users.management.domain.entity.BaseEntity;

public interface BaseServiceInterface<T extends BaseEntity> {

    T findById(UUID id);
    List<T> findAll();
    T save(T entity);
    void saveAll(List<T> entities);
    void delete(T entity);
    void deleteAll(List<T> entities);
}
