package com.vinhsang.vivmall.presentation.ui.activity.mainactivity.allfragment;

import com.vinhsang.vivmall.presentation.coremvp.MVPView;

/**
 * Created by Long on 7/8/2016.
 */

public interface AllView extends MVPView {

    void showProgress();

    void showContent();

    void onConnected();

    void onDisconnected();

    void onFetchError();
    void onUpdate();
    void showLoadingMore();
}
