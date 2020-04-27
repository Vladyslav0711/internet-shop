package org.example.internetshop.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T, K> {
    T create(T order);

    Optional<T> get(K orderId);

    T update(T order);

    boolean delete(K id);

    List<T> getAll();
}
