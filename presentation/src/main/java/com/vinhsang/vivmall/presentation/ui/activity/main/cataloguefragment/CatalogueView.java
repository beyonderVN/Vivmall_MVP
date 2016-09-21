package com.vinhsang.vivmall.presentation.ui.activity.main.cataloguefragment;

import com.vinhsang.vivmall.presentation.coremvp.MVPView;

/**
 * Created by Long on 7/8/2016.
 */

public interface CatalogueView extends MVPView {

    void showProgress();

    void showContent();

    void onConnected();

    void onDisconnected();

    void onFetchError();
    void init();
    void loadListTag();
    void finishLoadingMore();
    void startLoadingMore();
    void onUpdate();
}
