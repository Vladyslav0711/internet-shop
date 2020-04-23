package org.example.internetshop.service;

import java.util.List;
import java.util.Optional;
import org.example.internetshop.model.Item;

public interface ItemService {

    Item create(Item item);

    Optional<Item> get(Long id);

    List<Item> getAll();

    Item update(Item item);

    boolean delete(Long id);
}
