package com.longngo.footballfan.ui;

import android.app.Application;
import android.content.Context;

import com.longngo.footballfan.ui.di.DaggerMainComponent;
import com.longngo.footballfan.ui.di.MainComponent;
import com.longngo.footballfan.ui.di.MainModule;

/**
 * Created by Long on 10/5/2016.
 */

public class FootballFanApplication extends Application {

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
