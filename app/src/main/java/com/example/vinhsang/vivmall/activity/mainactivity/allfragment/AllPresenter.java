package com.example.vinhsang.vivmall.activity.mainactivity.allfragment;

import android.util.Log;

import com.example.vinhsang.vivmall.coremvp.SimpleMVPPresenter;
import com.example.vinhsang.vivmall.datamanager.DataLoadingSubject;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public class AllPresenter extends SimpleMVPPresenter<AllView,AllPresentationModel> {
    private static final String TAG = "AllPresenter";
    private final DataLoadingSubject dataManager;

    public DataLoadingSubject getDataManager() {
        return dataManager;
    }

    @Inject
    public AllPresenter(DataLoadingSubject dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(AllView mvpView, AllPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        if(presentationModel.shouldFetchRepositories()){
            getListItemProduct();
        }
    }

    public void getListItemProduct() {

        if (getMvpView() != null) {
            getMvpView().showProgress();
        }
            getPresentationModel().loadMore();
            showContent();
    }

    private void showContent() {

        Log.d(TAG, "showContent: "+getPresentationModel().getmItemProducts().size());
        if (getMvpView() != null) {
            getMvpView().showContent();
        }
    }

    private void onFetchError() {
        if (getMvpView() != null) {
            getMvpView().onFetchError();
        }
    }
    public void loadMore() {
        if (getMvpView() != null) {
            getMvpView().showLoadingMore();
        }
        try{
            getPresentationModel().loadMore();
            if (getMvpView() != null) {
                getMvpView().onLoadMore();
            }
        }catch (Exception e){
            e.getStackTrace();
            onFetchError();
        }

    }
}
