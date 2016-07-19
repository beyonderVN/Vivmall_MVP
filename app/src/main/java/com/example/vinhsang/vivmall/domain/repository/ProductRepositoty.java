package com.example.vinhsang.vivmall.domain.repository;

import com.example.vinhsang.vivmall.domain.ItemProduct;

import java.util.List;

import rx.Observable;

/**
 * Created by Long on 7/18/2016.
 */

public interface ProductRepositoty {

    /**
     * Get an {@link rx.Observable} which will emit a List of {@link ItemProduct}.
     */
    Observable<List<ItemProduct>> iListObservable();

    /**
     * Get an {@link rx.Observable} which will emit a {@link ItemProduct}.
     *
     * @param itemId The user id used to retrieve user data.
     */
    Observable<ItemProduct> itemProductObservable(final int itemId);
}
