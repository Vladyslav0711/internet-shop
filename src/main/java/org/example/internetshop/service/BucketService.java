package org.example.internetshop.service;

import org.example.internetshop.model.Bucket;

public interface BucketService {
    Bucket create(Bucket bucket);

    Bucket update(Bucket bucket);

    Bucket get(Long bucketId);

    boolean delete(Long bucketId);

    Bucket addItem(Long bucketId, Long itemId);

    boolean removeItem(Long bucketId, Long itemId);
}
