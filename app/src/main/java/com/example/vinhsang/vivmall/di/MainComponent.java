package com.example.vinhsang.vivmall.di;

import com.example.vinhsang.vivmall.activity.mainactivity.MainActivity;
import com.example.vinhsang.vivmall.activity.mainactivity.allfragment.AllFragment;
import com.example.vinhsang.vivmall.activity.mainactivity.cataloguefragment.CatalogueFragment;
import com.example.vinhsang.vivmall.datamanager.ConnectService;
import com.example.vinhsang.vivmall.datamanager.DataLoadingSubject;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Long on 7/8/2016.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {
    ConnectService getConnectService();
    DataLoadingSubject getDataManager();

    void inject(MainActivity mainActivity);
    void inject(AllFragment allFragment);
    void inject(CatalogueFragment catalogueFragment);
}
