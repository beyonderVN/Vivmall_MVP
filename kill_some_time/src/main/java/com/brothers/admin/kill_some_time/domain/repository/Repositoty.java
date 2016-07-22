package com.brothers.admin.kill_some_time.domain.repository;


import com.brothers.admin.kill_some_time.domain.Item;

import java.util.List;

import rx.Observable;

/**
 * Created by Long on 7/18/2016.
 */

public interface Repositoty {

    /**
     * Get an {@link Observable} which will emit a List of {@link Item}.
     */
    Observable<List<Item>> iListObservable();


}
