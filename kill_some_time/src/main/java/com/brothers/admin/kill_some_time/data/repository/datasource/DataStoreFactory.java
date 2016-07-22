package com.brothers.admin.kill_some_time.data.repository.datasource;

import android.content.Context;

import com.brothers.admin.kill_some_time.data.net.Api;
import com.brothers.admin.kill_some_time.data.net.HaiVlApi;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Long on 7/18/2016.
 *  * Factory that creates different implementations of {@link ItemDataStore}.
 */
@Singleton
public class DataStoreFactory {

    private final Context context;
    //private final ProductCache productCache;

    @Inject
    public DataStoreFactory(Context context) {
        if (context == null ) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();

    }


//    @Inject
//    public DataStoreFactory(Context context, ProductCache productCache) {
//        if (context == null || productCache == null) {
//            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
//        }
//        this.context = context.getApplicationContext();
//        this.productCache = productCache;
//    }
//
//    /**
//     * Create {@link } from a user id.
//     */
//    public ItemDataStore create(int userId) {
//        ItemDataStore itemProductDataStore;
//
//        if (!this.productCache.isExpired() && this.productCache.isCached(userId)) {
//            itemProductDataStore = new DiskItemProductDataStore(this.productCache);
//        } else {
//            itemProductDataStore = createCloudDataStore();
//        }
//
//        return itemProductDataStore;
//    }

    /**
     * Create {@link ItemDataStore} to retrieve data from the Cloud.
     */
    public ItemDataStore createCloudDataStore() {

        Api restApi = new HaiVlApi(this.context);

        return new CloudItemDataStore(restApi);
    }
}
