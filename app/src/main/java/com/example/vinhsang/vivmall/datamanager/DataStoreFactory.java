package com.example.vinhsang.vivmall.datamanager;

import android.content.Context;

import com.example.vinhsang.vivmall.datamanager.cache.ProductCache;
import com.example.vinhsang.vivmall.datamanager.source.CloudDataStore;
import com.example.vinhsang.vivmall.datamanager.source.DataStore;
import com.example.vinhsang.vivmall.datamanager.source.RestApi;
import com.example.vinhsang.vivmall.datamanager.source.RestApiImpl;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Long on 7/18/2016.
 *  * Factory that creates different implementations of {@link DataStore}.
 */
@Singleton
public class DataStoreFactory {

    private final Context context;
    private final ProductCache productCache;

    @Inject
    public DataStoreFactory(Context context, ProductCache productCache) {
        if (context == null || productCache == null) {
            throw new IllegalArgumentException("Constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.productCache = productCache;
    }

    /**
     * Create {@link } from a user id.
     */
    public UserDataStore create(int userId) {
        UserDataStore userDataStore;

        if (!this.productCache.isExpired() && this.productCache.isCached(userId)) {
            userDataStore = new DiskUserDataStore(this.productCache);
        } else {
            userDataStore = createCloudDataStore();
        }

        return userDataStore;
    }

    /**
     * Create {@link DataStore} to retrieve data from the Cloud.
     */
    public DataStore createCloudDataStore() {

        RestApi restApi = new RestApiImpl(this.context);

        return new CloudDataStore(restApi, this.productCache);
    }
}
