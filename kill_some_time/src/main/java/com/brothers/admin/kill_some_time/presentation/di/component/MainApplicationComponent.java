package com.brothers.admin.kill_some_time.presentation.di.component;

import android.content.Context;

import com.brothers.admin.kill_some_time.domain.repository.Repositoty;
import com.brothers.admin.kill_some_time.presentation.di.module.MainApplicationModule;
import com.brothers.admin.kill_some_time.presentation.ui.Main.MainActivity;
import com.brothers.admin.kill_some_time.presentation.ui.Main.haivlfragment.HaiVL_Fragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Long on 7/21/2016.
 */
@Singleton
@Component(modules = {MainApplicationModule.class})
public interface MainApplicationComponent {
    void inject(MainActivity mainActivity);
    void inject(HaiVL_Fragment haiVL_fragment);
    Context context();
    Repositoty repositoty();
}
