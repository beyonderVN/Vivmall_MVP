package com.vinhsang.vivmall.presentation.ui.activity.mainactivity.allfragment2;

import com.vinhsang.vivmall.presentation.coremvp.MVPView;

/**
 * Created by Long on 7/8/2016.
 */

public interface AllView2 extends MVPView {

    void showProgress();

    void showContent();

    void onConnected();

    void onDisconnected();
    void attachView();
    void onFetchError();
    void finishLoadingMore();
    void startLoadingMore();
    void onUpdate();
}
