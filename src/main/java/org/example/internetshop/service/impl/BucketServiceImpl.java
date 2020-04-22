package org.example.internetshop.service.impl;

import java.util.NoSuchElementException;
import org.example.internetshop.dao.BucketDao;
import org.example.internetshop.dao.ItemDao;
import org.example.internetshop.lib.Inject;
import org.example.internetshop.lib.Service;
import org.example.internetshop.model.Bucket;
import org.example.internetshop.model.Item;
import org.example.internetshop.service.BucketService;

@Service
public class BucketServiceImpl implements BucketService {
    @Inject
    private BucketDao bucketDao;
    @Inject
    private ItemDao itemDao;

    @Override
    public Bucket create(Bucket bucket) {
        return bucketDao.create(bucket);
    }

    @Override
    public Bucket update(Bucket bucket) {
        return bucketDao.update(bucket);
    }

    @Override
    public Bucket get(Long bucketId) {
        return bucketDao.get(bucketId);
    }

    @Override
    public boolean delete(Long bucketId) {
        return bucketDao.delete(bucketId);
    }

    @Override
    public Bucket addItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        Item item = itemDao.get(itemId).orElseThrow(() ->
                new NoSuchElementException("Can't find item with id " + itemId));
        bucket.getItems().add(item);
        return bucketDao.update(bucket);
    }

    @Override
    public boolean removeItem(Long bucketId, Long itemId) {
        Bucket bucket = bucketDao.get(bucketId);
        bucket.getItems().remove(itemId.intValue());
        return bucketDao.update(bucket) != null;
    }
}
