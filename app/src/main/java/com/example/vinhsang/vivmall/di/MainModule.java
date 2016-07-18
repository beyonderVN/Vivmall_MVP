package com.example.vinhsang.vivmall.di;

import android.content.Context;

import com.example.vinhsang.vivmall.datamanager.DataInterface;
import com.example.vinhsang.vivmall.datamanager.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Long on 7/8/2016.
 */
@Module
public class MainModule {
    private String baseUrl;
    private Context context;
    public MainModule(String baseUrl, Context context) {
        this.baseUrl = baseUrl;
        this.context = context;
    }

    @Provides @Singleton
    DataInterface getDataManager(){
        DataInterface dataManager = new DataManager(context);
        return dataManager;
    }
}
