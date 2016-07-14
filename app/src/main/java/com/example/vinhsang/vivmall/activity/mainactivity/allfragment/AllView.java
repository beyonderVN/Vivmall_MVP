package com.example.vinhsang.vivmall.activity.mainactivity.allfragment;

import com.example.vinhsang.vivmall.coremvp.MVPView;

/**
 * Created by Long on 7/8/2016.
 */

public interface AllView extends MVPView {

    void showProgress();

    void showContent();

    void onConnected();

    void onDisconnected();

    void onFetchError();
    void onLoadMore();
    void showLoadingMore();
}
