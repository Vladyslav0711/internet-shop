package org.example.internetshop.service;

import java.util.List;

public interface GenericService<T, K> {
    T create(T entity);

    T get(K id);

    List<T> getAll();

    T update(T entity);

    boolean delete(K id);
}
