package com.vinhsang.vivmall.presentation.ui.activity.mainactivity.allfragment;


import android.util.Log;

import com.vinhsang.vivmall.domain.ItemProduct;
import com.vinhsang.vivmall.domain.interactor.DefaultSubscriber;
import com.vinhsang.vivmall.domain.interactor.UseCase;
import com.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;
import com.vinhsang.vivmall.presentation.mapper.ItemProductModelDataMapper;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Long on 7/8/2016.
 */

public class AllPresenter extends SimpleMVPPresenter<AllView, AllPresentationModel> {

    private static final String TAG = "AllPresenter";
    private final ItemProductModelDataMapper itemProductModelDataMapper;
    private final UseCase useCase;

    @Inject
    public AllPresenter(ItemProductModelDataMapper itemProductModelDataMapper, @Named("userList") UseCase useCase) {
        this.itemProductModelDataMapper = itemProductModelDataMapper;
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
            this.useCase.execute(new LoadMoreListSubscriber(), getPresentationModel().getLastItem());
        } catch (Exception e) {
            e.getStackTrace();
            onFetchError();
        }

    }

    public void resetData() {
        getPresentationModel().reset();
        getFirstPageProductList();
    }

    private void getFirstPageProductList() {
        if (getPresentationModel().shouldFetchRepositories()) {
            if (getMvpView() != null) {
                getMvpView().showProgress();
            }
            this.useCase.execute(new LoadFirstSubscriber(), getPresentationModel().getLastItem());
        }else {
            if (getMvpView() != null) {
                getMvpView().showContent();
            }
        }


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

            getPresentationModel().loadMore(itemProductModelDataMapper.transform(itemProducts));
            showContent();
        }
    }

    private class LoadMoreListSubscriber extends DefaultSubscriber<List<ItemProduct>> {

        @Override
        public void onCompleted() {
            ;
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

            if (itemProducts.size() == 0) {
                getPresentationModel().setNoMore(true);
            }
            if (getMvpView() != null) {
                getMvpView().finishLoadingMore();
            }
            getPresentationModel().loadMore(itemProductModelDataMapper.transform(itemProducts));
            if (getMvpView() != null) {
                getMvpView().onUpdate();
            }

        }
    }


}
