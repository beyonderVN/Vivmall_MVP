package com.example.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment;


import com.example.vinhsang.vivmall.presentation.view.activity.base.OneInterfaceForAll;
import com.example.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;
import com.example.vinhsang.vivmall.data.datamanager.DataInterface;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public class AllPresenter extends SimpleMVPPresenter<AllView,AllPresentationModel> implements OneInterfaceForAll {
    public enum Work {
        RESET_LIST
    }
    private static final String TAG = "AllPresenter";
    private final DataInterface dataManager;

    @Inject
    public AllPresenter(DataInterface dataManager) {
        this.dataManager = dataManager;
    }

    @Override
    public void attachView(AllView mvpView, AllPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        registerCallback(this);
            init();
    }

    public void init() {
        registerCallback(this);
        if (getMvpView() != null) {
            getMvpView().showProgress();
        }
            getPresentationModel().loadMore(dataManager.itemProducts());
            showContent();
    }

    private void showContent() {

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
            getPresentationModel().loadMore(dataManager.itemProducts());
            if (getMvpView() != null) {
                getMvpView().onUpdate();
            }
        }catch (Exception e){
            e.getStackTrace();
            onFetchError();
        }

    }

    @Override
    public void registerCallback(OneInterfaceForAll callbacks) {
        getPresentationModel().registerCallback(this);
    }

    @Override
    public void unregisterCallback(OneInterfaceForAll callbacks) {
        getPresentationModel().unregisterCallback(this);
    }


    @Override
    public void update(Object work, Object object) {
        switch ((Work) work){
            case RESET_LIST:
                if (getMvpView() != null) {
                    getMvpView().onUpdate();
                }
                break;
        }

    }
}
