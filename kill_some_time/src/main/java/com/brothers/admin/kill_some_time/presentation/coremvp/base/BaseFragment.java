package com.brothers.admin.kill_some_time.presentation.coremvp.base;

import android.support.annotation.NonNull;

import com.brothers.admin.kill_some_time.presentation.coremvp.MVPFragment;
import com.brothers.admin.kill_some_time.presentation.coremvp.MVPPresenter;
import com.brothers.admin.kill_some_time.presentation.coremvp.MVPView;

import java.io.Serializable;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public abstract class BaseFragment<M extends Serializable, V extends MVPView, P extends MVPPresenter<V, M>>
        extends MVPFragment<M, V, P> {
    @Inject
    protected P presenter;

    @NonNull
    @Override protected P createPresenter() {
        //this field will be populated by field injeciton from dagger
        // your presenter should not accept the presentationModel as its constructor's paramteter.
        // Instead, it will be provided by #attachView method.
        return presenter;
    }
}