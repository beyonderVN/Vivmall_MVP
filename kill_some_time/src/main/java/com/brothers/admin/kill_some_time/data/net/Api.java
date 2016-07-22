package com.brothers.admin.kill_some_time.data.net;

import com.brothers.admin.kill_some_time.domain.Item;

import java.util.List;

import rx.Observable;

/**
 * Created by Admin on 16/07/2016.
 */

public interface Api {
    Observable<List<Item>> getSource();
}
