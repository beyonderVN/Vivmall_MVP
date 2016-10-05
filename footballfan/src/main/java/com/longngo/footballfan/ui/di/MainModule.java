package com.longngo.footballfan.ui.di;

import android.content.Context;

import com.longngo.footballfan.data.datamanager.FootballService;
import com.longngo.footballfan.data.datamanager.FootballServiceFactory;
import com.longngo.footballfan.ui.FootballFanApplication;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Long on 7/8/2016.
 */
@Module
public class MainModule {
    private Context context;
    private final FootballFanApplication application;

    public MainModule(FootballFanApplication application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    FootballService provideBourbonService() {
        return FootballServiceFactory.makeService();
    }



}

