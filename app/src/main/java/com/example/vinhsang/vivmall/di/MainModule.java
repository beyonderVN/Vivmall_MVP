package com.example.vinhsang.vivmall.di;

import com.example.vinhsang.vivmall.datamanager.ConnectService;
import com.example.vinhsang.vivmall.datamanager.DataLoadingSubject;
import com.example.vinhsang.vivmall.datamanager.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Long on 7/8/2016.
 */
@Module
public class MainModule {
    private String baseUrl;

    public MainModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }
    @Provides
    ConnectService getConnectService(){
        ConnectService connectService = new ConnectService();
        connectService.setUrl(baseUrl);
        return connectService;
    }

    @Provides
    DataLoadingSubject getDataManager(){
        DataLoadingSubject dataManager = DataManager.getInstance();
        return dataManager;
    }
}
