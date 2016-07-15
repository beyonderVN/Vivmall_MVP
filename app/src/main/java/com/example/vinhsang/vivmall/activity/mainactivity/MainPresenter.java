package com.example.vinhsang.vivmall.activity.mainactivity;

import com.example.vinhsang.vivmall.coremvp.SimpleMVPPresenter;
import com.example.vinhsang.vivmall.datamanager.DataManager;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public class MainPresenter extends SimpleMVPPresenter<MainView,MainPresentationModel> {
    @Inject
    public MainPresenter() {
    }

    //fortest
    public void resetListItemProduct(){
        DataManager.getInstance().getAllPresentationMOdel().clearListItemProduct();
    }

}
