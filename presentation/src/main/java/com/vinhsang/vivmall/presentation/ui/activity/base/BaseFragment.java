package com.vinhsang.vivmall.presentation.ui.activity.base;

import android.support.annotation.NonNull;

import com.vinhsang.vivmall.presentation.coremvp.MVPFragment;
import com.vinhsang.vivmall.presentation.coremvp.MVPPresenter;
import com.vinhsang.vivmall.presentation.coremvp.MVPView;
import com.vinhsang.vivmall.presentation.navigation.Navigator;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public abstract class BaseFragment<M extends Serializable, V extends MVPView, P extends MVPPresenter<V, M>>
        extends MVPFragment<M, V, P> {
    @Inject
    protected P presenter;
    @Inject
    protected Navigator navigator;
    @NonNull
    @Override protected P createPresenter() {
        //this field will be populated by field injeciton from dagger
        // your presenter should not accept the presentationModel as its constructor's paramteter.
        // Instead, it will be provided by #attachView method.
        return presenter;
    }
}