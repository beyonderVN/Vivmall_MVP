package com.vinhsang.vivmall.data.bourbon;


import com.example.vinhsang.data.BuildConfig;
import com.vinhsang.vivmall.data.bourbon.model.Comment;
import com.vinhsang.vivmall.data.bourbon.model.Shot;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import rx.Single;

@Singleton
public class DataManager {

    private final BourbonService mBourbonService;

    @Inject
    public DataManager(BourbonService bourbonService) {
        mBourbonService = bourbonService;
    }

    public Single<List<Shot>> getShots(int perPage, int page) {
        return mBourbonService.getShots(BuildConfig.DRIBBBLE_ACCESS_TOKEN, perPage, page);
    }

    public Single<List<Comment>> getComments(int id, int perPage, int page) {
        return mBourbonService.getComments(id, BuildConfig.DRIBBBLE_ACCESS_TOKEN, perPage, page);
    }
}