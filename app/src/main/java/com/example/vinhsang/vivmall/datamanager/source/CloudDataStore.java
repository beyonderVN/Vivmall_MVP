package com.example.vinhsang.vivmall.datamanager.source;

import com.example.vinhsang.vivmall.datamanager.cache.ProductCache;
import com.example.vinhsang.vivmall.datamanager.entity.ItemProductEntity;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Long on 7/18/2016.
 */

public class CloudDataStore implements DataStore {
    private final RestApi restApi;
    private final ProductCache productCache;

    public CloudDataStore(RestApi restApi, ProductCache productCache) {
        this.restApi = restApi;
        this.productCache = productCache;
    }
    private final Action1<ItemProductEntity> saveToCacheAction = itemProductEntity -> {
        if (itemProductEntity != null) {
            CloudDataStore.this.productCache.put(itemProductEntity);
        }
    };
    @Override
    public Observable<List<ItemProductEntity>> iEntityListObservable() {
        return this.restApi.itemEntityListObservable();
    }

    @Override
    public Observable<ItemProductEntity> itemEntityDetails(int itemId) {
        return this.restApi.itemProductEntityObservable(itemId);
    }
}
