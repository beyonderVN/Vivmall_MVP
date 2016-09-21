package com.vinhsang.vivmall.data.vivmallapi;

import com.vinhsang.vivmall.data.entity.ItemProductEntity;

import java.util.List;

import rx.Observable;

/**
 * Created by Long on 7/18/2016.
 */
public interface RestApi {
    /**
     * Retrieves an {@link rx.Observable} which will emit a List of {@link ItemProductEntity}.
     */
    Observable<List<ItemProductEntity>> itemEntityListObservable();

    /**
     * Retrieves an {@link rx.Observable} which will emit a {@link ItemProductEntity}.
     *
     * @param userId The user id used to get user data.
     */
    Observable<ItemProductEntity> itemProductEntityObservable(final int userId);
}
