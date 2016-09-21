package com.vinhsang.vivmall.presentation.ui.activity.mainactivity;

import android.util.Log;

import com.vinhsang.vivmall.domain.interactor.DefaultSubscriber;
import com.vinhsang.vivmall.domain.ItemProduct;
import com.vinhsang.vivmall.domain.interactor.ProductCase;
import com.vinhsang.vivmall.presentation.mapper.ItemProductModelDataMapper;
import com.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public class MainPresenter extends SimpleMVPPresenter<MainView, MainPresentationModel> {
    private static final String TAG = "MainPresenter";
    private final ProductCase productCase;
    private final ItemProductModelDataMapper itemProductModelDataMapper;

    @Inject
    public MainPresenter(ProductCase productCase,ItemProductModelDataMapper itemProductModelDataMapper) {
        this.productCase = productCase;
        this.itemProductModelDataMapper = itemProductModelDataMapper;

    }

    //fortest
    public void resetListItemProduct(){
        //DataManager.getInstance().getAllPresentationMOdel().clearListItemProduct();
    }

    @Override
    public void attachView(MainView mvpView, MainPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        getUserList();
    }

    private void getUserList() {
        this.productCase.execute(new ProductListSubscriber());

    }

    private final class ProductListSubscriber extends DefaultSubscriber<List<ItemProduct>> {

        @Override public void onCompleted() {
            Log.d(TAG, "onCompleted: ");
        }

        @Override public void onError(Throwable e) {
//            UserListPresenter.this.hideViewLoading();
//            UserListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
//            UserListPresenter.this.showViewRetry();
        }

        @Override public void onNext(List<ItemProduct> users) {
            //UserListPresenter.this.showUsersCollectionInView(users);
            Log.d(TAG, "onNext: " +users.size());
        }
    }
}
