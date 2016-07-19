package com.vinhsang.vivmall.data.repository.datasource;


import com.vinhsang.vivmall.data.cache.ProductCache;
import com.vinhsang.vivmall.data.entity.ItemProductEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Long on 7/19/2016.
 */
public class DiskItemProductDataStore implements ItemProductDataStore {
    private final ProductCache productCache;

    public DiskItemProductDataStore(ProductCache productCache) {
        this.productCache = productCache;
    }

    @Override
    public Observable<List<ItemProductEntity>> iEntityListObservable() {
        //TODO: implement simple cache for storing/retrieving collections of users.
        throw new UnsupportedOperationException("Operation is not available!!!");
    }

    @Override
    public Observable<ItemProductEntity> itemEntityDetails(int userId) {
        return this.productCache.get(userId);
    }
}
