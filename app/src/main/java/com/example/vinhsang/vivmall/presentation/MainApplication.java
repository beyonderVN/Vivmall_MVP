package com.example.vinhsang.vivmall.presentation;

import android.app.Application;
import android.content.Context;

import com.example.vinhsang.vivmall.presentation.internal.di.DaggerMainComponent;
import com.example.vinhsang.vivmall.presentation.internal.di.MainComponent;
import com.example.vinhsang.vivmall.presentation.internal.di.MainModule;

/**
 * Created by Long on 7/8/2016.
 */

public class MainApplication extends Application {
    final static String baseUrl = "http://10.0.12.14:8080/SrvAppVivmall/RestSrv/SrvConnect/";
    public static Context mContext;
    static MainComponent mainComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        setupGraph();
    }


    void setupGraph(){
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainModule(this))
                .build();

    }

    public static MainComponent getMainComponent() {
        return mainComponent;
    }
}
