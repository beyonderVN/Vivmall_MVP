package com.vinhsang.vivmall.presentation.ui.activity.main;

import com.vinhsang.vivmall.data.bourbon.model.Comment;
import com.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;

import java.util.List;

import javax.inject.Inject;

import rx.Single;
import rx.SingleSubscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Long on 7/8/2016.
 */

public class MainPresenter extends SimpleMVPPresenter<MainView, MainPresentationModel> {
    private static final String TAG = "MainPresenter";


    @Inject
    public MainPresenter() {


    }

    //fortest
    public void resetListItemProduct(){
        //DataManager.getInstance().getAllPresentationMOdel().clearListItemProduct();
    }

    @Override
    public void attachView(MainView mvpView, MainPresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
    }


}
