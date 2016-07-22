package com.brothers.admin.kill_some_time.data.repository.datasource;


import com.brothers.admin.kill_some_time.data.net.Api;
import com.brothers.admin.kill_some_time.domain.Item;

import java.util.List;

import rx.Observable;

/**
 * Created by Long on 7/18/2016.
 */

public class CloudItemDataStore implements ItemDataStore {
    private final Api restApi;

    public CloudItemDataStore(Api restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<List<Item>> iEntityListObservable() {
        return this.restApi.getSource();
    }

}
