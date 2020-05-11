package org.example.internetshop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {
    T create(T entity);

    Optional<T> get(K id);

    T update(T entity);

    boolean delete(K id);

    List<T> getAll();
}
