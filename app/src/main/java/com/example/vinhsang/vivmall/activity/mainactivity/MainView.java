package com.example.vinhsang.vivmall.activity.mainactivity;

import com.example.vinhsang.vivmall.coremvp.MVPView;

/**
 * Created by Long on 7/8/2016.
 */

public interface MainView extends MVPView {
    void showProgress();

    void showContent();

    void onConnected();

    void onDisconnected();
}
