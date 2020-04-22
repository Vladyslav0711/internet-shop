package org.example.internetshop.dao;

import org.example.internetshop.model.Bucket;

public interface BucketDao {
    Bucket create(Bucket bucket);

    Bucket update(Bucket bucket);

    Bucket get(Long bucketId);

    Boolean delete(Long bucketId);
}
