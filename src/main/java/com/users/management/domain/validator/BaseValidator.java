package com.users.management.domain.validator;

import com.users.management.domain.entity.BaseEntity;

public class BaseValidator<T extends BaseEntity> implements BaseValidatorInterface<T> {

    public void validateCreate(final T entity) {
    }

    public void validateUpdate(final T entity) {
    }

    public void validateDelete(final T entity) {
    }
}
