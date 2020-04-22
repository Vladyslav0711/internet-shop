package org.example.internetshop.dao;

import java.util.ArrayList;
import java.util.List;
import org.example.internetshop.model.Bucket;
import org.example.internetshop.model.Item;

public class Storage {
    public static final List<Item> items = new ArrayList<>();
    public static final List<Bucket> buckets = new ArrayList<>();
    private static Long idItem = 0L;

    public static void addItemToList(Item item) {
        items.add(item);
        item.setId(++idItem);
    }
}
