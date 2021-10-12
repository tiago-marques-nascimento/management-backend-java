package com.users.management.domain.service;

import java.util.UUID;
import java.util.List;
import java.util.Optional;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Isolation;
import com.google.common.collect.ImmutableList;
import com.users.management.domain.entity.BaseEntity;
import com.users.management.domain.repository.BaseRepositoryInterface;
import com.users.management.domain.validator.BaseValidatorInterface;

public abstract class BaseService<T extends BaseEntity> implements BaseServiceInterface<T> {

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public T findById(final UUID id) {

        Optional<T> retorno = this.getBaseRepository().findById(id);
        return retorno.isPresent() ? retorno.get() : null;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public List<T> findAll() {

        return ImmutableList.copyOf(this.getBaseRepository().findAll());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public T save(final T entity) {

        if (entity.isNew()) {
            entity.create();
            this.getBaseValidator().validateCreate(entity);
        } else {
            this.getBaseValidator().validateUpdate(entity);
        }
        return this.getBaseRepository().save(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void saveAll(final List<T> entities) {

        entities.forEach(entity -> {
            if (entity.isNew()) {
                entity.create();
                this.getBaseValidator().validateCreate(entity);
            } else {
                this.getBaseValidator().validateUpdate(entity);
            }
        });
        this.getBaseRepository().saveAll(entities);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void delete(final T entity) {

        this.getBaseValidator().validateDelete(entity);
        this.getBaseRepository().delete(entity);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public void deleteAll(final List<T> entities) {

        entities.forEach(entity -> {
            this.getBaseValidator().validateDelete(entity);
        });
        this.getBaseRepository().deleteAll(entities);
    }

    protected abstract BaseRepositoryInterface<T> getBaseRepository();
    protected abstract BaseValidatorInterface<T> getBaseValidator();
}
