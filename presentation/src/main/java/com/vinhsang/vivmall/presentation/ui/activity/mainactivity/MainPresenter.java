package com.vinhsang.vivmall.presentation.ui.activity.mainactivity;

import com.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;

import javax.inject.Inject;

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
