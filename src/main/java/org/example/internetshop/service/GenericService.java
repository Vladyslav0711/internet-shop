package org.example.internetshop.service;

import java.util.List;

public interface GenericService<T, K> {
    T create(T elem);

    T get(K id);

    List<T> getAll();

    T update(T elem);

    boolean delete(K id);
}
