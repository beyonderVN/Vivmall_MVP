package com.brothers.admin.kill_some_time.presentation.ui.Main.haivlfragment;

import android.util.Log;

import com.brothers.admin.kill_some_time.domain.Item;
import com.brothers.admin.kill_some_time.domain.interactor.DefaultSubscriber;
import com.brothers.admin.kill_some_time.domain.interactor.UseCase;
import com.brothers.admin.kill_some_time.presentation.coremvp.SimpleMVPPresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Long on 7/22/2016.
 */

public class HaiVlPresenter extends SimpleMVPPresenter<HaiVlView,HaiVLModel> {
    private static final String TAG = "HaiVlPresenter";
    final UseCase useCase;

    @Inject
    public HaiVlPresenter(UseCase useCase) {
        this.useCase = useCase;
    }


    @Override
    public void attachView(HaiVlView mvpView, HaiVLModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        useCase.execute(new HaiVLSubscriber());
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    class HaiVLSubscriber extends DefaultSubscriber<List<Item>>{
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Item> items) {
            super.onNext(items);
            Log.d(TAG, "onNext: items "+ items.size());
            getPresentationModel().setItems(items);
        }
    }
}

