package com.brothers.admin.kill_some_time.presentation;

import android.app.Application;

import com.brothers.admin.kill_some_time.presentation.di.DaggerMainComponent;
import com.brothers.admin.kill_some_time.presentation.di.component.MainApplicationComponent;
import com.brothers.admin.kill_some_time.presentation.di.module.MainApplicationModule;

/**
 * Created by Long on 7/21/2016.
 */

public class MainApplication extends Application {
    static MainApplicationComponent mainComponent;
    @Override
    public void onCreate() {
        super.onCreate();
        setupGraph();
    }
    private void setupGraph(){
        mainComponent = DaggerMainComponent.builder()
                .mainModule(new MainApplicationModule(this))
                .build();
    }
    public static MainApplicationComponent getMainComponent(){
        return mainComponent;
    }
}
