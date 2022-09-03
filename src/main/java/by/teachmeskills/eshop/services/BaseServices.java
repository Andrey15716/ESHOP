package by.teachmeskills.eshop.services;

import by.teachmeskills.eshop.entities.BaseEntity;

import java.util.List;

public interface BaseServices<T extends BaseEntity> {
    T create(T entity);

    List<T> read();

    T update(T entity);

    void delete(int id);
}