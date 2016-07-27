package com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment;


import android.util.Log;

import com.vinhsang.vivmall.data.datamanager.DataInterface;
import com.vinhsang.vivmall.domain.ItemProduct;
import com.vinhsang.vivmall.domain.interactor.DefaultSubscriber;
import com.vinhsang.vivmall.domain.interactor.UseCase;
import com.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Long on 7/8/2016.
 */

public class AllPresenter extends SimpleMVPPresenter<AllView, AllPresentationModel>  {

    private static final String TAG = "AllPresenter";
    private final DataInterface dataManager;
    private final UseCase useCase;

    @Inject
    public AllPresenter(DataInterface dataManager,@Named("userList") UseCase useCase) {
        this.dataManager = dataManager;
        this.useCase = useCase;
    }

    @Override
    public void attachView(AllView mvpView, AllPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);

        getFirstPageProductList();
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
        try {
            this.useCase.execute(new LoadMoreListSubscriber(),getPresentationModel().getLastItem());
            if (getMvpView() != null) {
                getMvpView().onUpdate();
            }
        } catch (Exception e) {
            e.getStackTrace();
            onFetchError();
        }

    }


    private void getFirstPageProductList() {
        Log.d(TAG, "getFirstPageProductList: ");
        if (getMvpView() != null) {
            getMvpView().showProgress();
        }
        this.useCase.execute(new LoadFirstSubscriber(),getPresentationModel().getLastItem());

    }

    private class LoadFirstSubscriber extends DefaultSubscriber<List<ItemProduct>> {

        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
//            UserListPresenter.this.hideViewLoading();
//            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
//            UserListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<ItemProduct> itemProducts) {
            //UserListPresenter.this.showUsersCollectionInView(users);
            Log.d(TAG, "onNext: " + itemProducts.size());
            getPresentationModel().loadMore(itemProducts);
            showContent();
        }
    }

    private class LoadMoreListSubscriber extends DefaultSubscriber<List<ItemProduct>> {

        @Override
        public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override
        public void onError(Throwable e) {
//            UserListPresenter.this.hideViewLoading();
//            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
//            UserListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<ItemProduct> itemProducts) {
            //UserListPresenter.this.showUsersCollectionInView(users);
            Log.d(TAG, "onNext: " + itemProducts.size());
            getPresentationModel().loadMore(itemProducts);
            if (getMvpView() != null) {
                getMvpView().onUpdate();
            }
        }
    }


}
