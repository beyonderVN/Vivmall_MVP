package com.vinhsang.vivmall.data.dribbble;


import com.example.vinhsang.data.BuildConfig;
import com.vinhsang.vivmall.data.dribbble.model.Comment;
import com.vinhsang.vivmall.data.dribbble.model.Shot;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;

@Singleton
public class DataManager {

    private final DribbbleService mDribbbleService;

    @Inject
    public DataManager(DribbbleService dribbbleService) {
        mDribbbleService = dribbbleService;
    }

    public Single<List<Shot>> getShots(int perPage, int page) {
        return mDribbbleService.getShots(BuildConfig.DRIBBBLE_ACCESS_TOKEN, perPage, page);
    }

    public Single<List<Comment>> getComments(int id, int perPage, int page) {
        return mDribbbleService.getComments(id, BuildConfig.DRIBBBLE_ACCESS_TOKEN, perPage, page);
    }
}