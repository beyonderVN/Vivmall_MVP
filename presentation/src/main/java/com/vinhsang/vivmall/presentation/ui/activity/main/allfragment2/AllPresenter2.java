package com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2;


import android.util.Log;

import com.vinhsang.vivmall.data.bourbon.DataManager;
import com.vinhsang.vivmall.data.bourbon.model.Shot;
import com.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;
import com.vinhsang.vivmall.presentation.mapper.ItemShotMapper;
import com.vinhsang.vivmall.presentation.model.BaseModel;

import java.util.List;

import javax.inject.Inject;

import rx.SingleSubscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Long on 7/8/2016.
 */

public class AllPresenter2 extends SimpleMVPPresenter<AllView2, AllPresentationModel2> {

    private static final String TAG = "AllPresenter2";


    DataManager mDataManager;
    private Subscription mSubscription;


    @Inject
    public AllPresenter2( DataManager dataManager) {
        mDataManager=dataManager;
    }

    @Override
    public void attachView(AllView2 mvpView, AllPresentationModel2 presentationModel) {
        super.attachView(mvpView, presentationModel);

        getFirstPageProductList();
    }

    private void showContent() {

        if (getMvpView() != null) {
            getMvpView().showContent();
            getMvpView().onUpdate();
        }
    }

    private void onFetchError() {
        if (getMvpView() != null) {
            getMvpView().onFetchError();
        }
    }

    public void loadMore() {
        if (getMvpView() != null) {
            getMvpView().startLoadingMore();
        }
        try {
            getMoreShots(mPerPage, mPage);
        } catch (Exception e) {
            e.getStackTrace();
            onFetchError();
        }

    }

    public void resetData() {
        getPresentationModel().reset();
        getFirstPageProductList();
        mPage =0;
    }

    private void getFirstPageProductList() {
        if (getPresentationModel().shouldFetchRepositories()) {
            if (getMvpView() != null) {
                getMvpView().showProgress();
            }
            getFirstShots(mPerPage, mPage);
        }else {
            if (getMvpView() != null) {
                // TODO: 9/21/2016 show empty state
                getMvpView().showContent();
            }
        }


    }
    int mPage = 1;
    int mPerPage = 10;
    public void getFirstShots(int perPage, int page) {
        mSubscription = mDataManager.getShots(perPage, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<Shot>>() {
                    @Override
                    public void onSuccess(List<Shot> shots) {

                        if (!shots.isEmpty()) {
                            Log.d(TAG, "onSuccess: "+shots.toString());
                            updateLoadMore(ItemShotMapper.transform(shots));
                            mPage++;
                            showContent();
                        } else {
                            Log.d(TAG, "onSuccess: is empty");
                            getPresentationModel().setNoMore(true);
                            showContent();
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d(TAG, "onError: ");
                    }
                });
    }

    public void getMoreShots(int perPage, int page) {
        mSubscription = mDataManager.getShots(perPage, page)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new SingleSubscriber<List<Shot>>() {
                    @Override
                    public void onSuccess(List<Shot> shots) {

                        if (!shots.isEmpty()) {
                            Log.d(TAG, "onSuccess: "+shots.toString());
                            if (shots.size() == 0) {
                                getPresentationModel().setNoMore(true);
                            }
                            if (getMvpView() != null) {
                                getMvpView().finishLoadingMore();
                            }
                            updateLoadMore(ItemShotMapper.transform(shots));
                            mPage++;
                        } else {
                            Log.d(TAG, "onSuccess: is empty");
                            getPresentationModel().setNoMore(true);
                            showContent();
                        }
                    }

                    @Override
                    public void onError(Throwable error) {
                        Log.d(TAG, "onError: ");
                    }
                });
    }

    private void updateLoadMore(List<BaseModel> shots){
        getPresentationModel().loadMore(shots);
        if (getMvpView() != null) {
            getMvpView().onUpdate();
        }

    }

}
