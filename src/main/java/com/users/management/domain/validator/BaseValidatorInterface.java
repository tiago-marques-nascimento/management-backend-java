package com.users.management.domain.validator;

import com.users.management.domain.entity.BaseEntity;

public interface BaseValidatorInterface<T extends BaseEntity> {

    void validateCreate(T entity);
    void validateUpdate(T entity);
    void validateDelete(T entity);
}
