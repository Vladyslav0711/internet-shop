package org.example.internetshop.service.impl;

import java.util.List;
import java.util.Optional;
import org.example.internetshop.dao.ItemDao;
import org.example.internetshop.lib.Inject;
import org.example.internetshop.lib.Service;
import org.example.internetshop.model.Item;
import org.example.internetshop.service.ItemService;

@Service
public class ItemServiceImpl implements ItemService {
    @Inject
    private ItemDao itemDao;

    @Override
    public Item create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public Optional<Item> get(Long id) {
        return itemDao.get(id);
    }

    @Override
    public List<Item> getAll() {
        return itemDao.getAll();
    }

    @Override
    public Item update(Item item) {
        return itemDao.update(item);
    }

    @Override
    public boolean delete(Long id) {
        return itemDao.delete(id);
    }

}
