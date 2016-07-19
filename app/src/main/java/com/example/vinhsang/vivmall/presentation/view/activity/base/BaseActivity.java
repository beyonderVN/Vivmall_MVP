package com.example.vinhsang.vivmall.presentation.view.activity.base;


import android.support.annotation.NonNull;

import com.example.vinhsang.vivmall.presentation.coremvp.MVPActivity;
import com.example.vinhsang.vivmall.presentation.coremvp.MVPPresenter;
import com.example.vinhsang.vivmall.presentation.coremvp.MVPView;
import com.example.vinhsang.vivmall.presentation.navigation.Navigator;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public abstract class BaseActivity<M extends Serializable, V extends MVPView, P extends MVPPresenter<V, M>>
extends MVPActivity <M,V,P>{
    @Inject protected P presenter;
    @Inject
    Navigator navigator;
    @NonNull
    @Override
    protected P createPresenter() {
        return presenter;
    }
}
