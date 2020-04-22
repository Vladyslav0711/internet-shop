package org.example.internetshop.dao.impl;

import java.util.NoSuchElementException;
import org.example.internetshop.dao.BucketDao;
import org.example.internetshop.dao.Storage;
import org.example.internetshop.lib.Dao;
import org.example.internetshop.model.Bucket;

@Dao
public class BucketDaoImpl implements BucketDao {

    @Override
    public Bucket create(Bucket bucket) {
        Storage.buckets.add(bucket);
        return bucket;
    }

    @Override
    public Bucket update(Bucket bucket) {
        Long id = Storage.buckets
                .stream()
                .filter(b -> b.equals(bucket))
                .map(Bucket::getId)
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket for update"));
        Storage.buckets.set(id.intValue(), bucket);
        return bucket;
    }

    @Override
    public Bucket get(Long bucketId) {
        return Storage.buckets
                .stream()
                .filter(b -> b.getId().equals(bucketId))
                .findFirst()
                .orElseThrow(() ->
                        new NoSuchElementException("Can't find bucket with id " + bucketId));
    }

    @Override
    public Boolean delete(Long bucketId) {
        return Storage.buckets.remove(bucketId.intValue()) != null;
    }
}
