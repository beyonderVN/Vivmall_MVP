package com.longngo.footballfan.ui.di;

import android.content.Context;

import com.longngo.footballfan.data.datamanager.DataManager;
import com.longngo.footballfan.data.datamanager.FootballService;
import com.longngo.footballfan.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Long on 7/8/2016.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    DataManager dataManager();
    FootballService footballService();

    void inject(MainActivity mainActivity);


    Context context();
}
