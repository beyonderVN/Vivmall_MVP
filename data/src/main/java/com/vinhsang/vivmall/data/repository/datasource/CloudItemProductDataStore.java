package com.vinhsang.vivmall.data.repository.datasource;


import com.vinhsang.vivmall.data.cache.ProductCache;
import com.vinhsang.vivmall.data.entity.ItemProductEntity;
import com.vinhsang.vivmall.data.net.RestApi;

import java.util.List;

import rx.Observable;
import rx.functions.Action1;

/**
 * Created by Long on 7/18/2016.
 */

public class CloudItemProductDataStore implements ItemProductDataStore {
    private final RestApi restApi;
    private final ProductCache productCache;

    public CloudItemProductDataStore(RestApi restApi, ProductCache productCache) {
        this.restApi = restApi;
        this.productCache = productCache;
    }
    private final Action1<ItemProductEntity> saveToCacheAction = new Action1<ItemProductEntity>() {
        @Override
        public void call(ItemProductEntity itemProductEntity) {
            if (itemProductEntity != null) {
                CloudItemProductDataStore.this.productCache.put(itemProductEntity);
            }
        }
    };

    @Override
    public Observable<List<ItemProductEntity>> iEntityListObservable() {
        return this.restApi.itemEntityListObservable();
    }

    @Override
    public Observable<ItemProductEntity> itemEntityDetails(int itemId) {
        return this.restApi.itemProductEntityObservable(itemId).doOnNext(saveToCacheAction);
    }
}