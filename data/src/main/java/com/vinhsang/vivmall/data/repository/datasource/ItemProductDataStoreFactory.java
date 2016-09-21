package com.vinhsang.vivmall.data.repository.datasource;

import android.content.Context;

import com.vinhsang.vivmall.data.cache.ProductCache;
import com.vinhsang.vivmall.data.entity.mapper.ProductEntityJsonMapper;
import com.vinhsang.vivmall.data.vivmallapi.RestApi;
import com.vinhsang.vivmall.data.vivmallapi.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Long on 7/18/2016.
 *  * Factory that creates different implementations of {@link ItemProductDataStore}.
 */
@Singleton
public class ItemProductDataStoreFactory {

    private final Context context;
    private final ProductCache productCache;

    @Inject
    public ItemProductDataStoreFactory(Context context, ProductCache productCache) {
        if (context == null || productCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.productCache = productCache;
    }

    /**
     * Create {@link } from a user id.
     */
    public ItemProductDataStore create(int userId) {
        ItemProductDataStore itemProductDataStore;

        if (!this.productCache.isExpired() && this.productCache.isCached(userId)) {
            itemProductDataStore = new DiskItemProductDataStore(this.productCache);
        } else {
            itemProductDataStore = createCloudDataStore();
        }

        return itemProductDataStore;
    }

    /**
     * Create {@link ItemProductDataStore} to retrieve data from the Cloud.
     */
    public ItemProductDataStore createCloudDataStore() {
        ProductEntityJsonMapper productEntityJsonMapper = new ProductEntityJsonMapper();
        RestApi restApi = new RestApiImpl(this.context,productEntityJsonMapper);

        return new CloudItemProductDataStore(restApi, this.productCache);
    }
}
