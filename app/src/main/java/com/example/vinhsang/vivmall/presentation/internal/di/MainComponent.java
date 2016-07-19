package com.example.vinhsang.vivmall.presentation.internal.di;

import android.content.Context;

import com.example.vinhsang.vivmall.domain.executor.PostExecutionThread;
import com.example.vinhsang.vivmall.domain.executor.ThreadExecutor;
import com.example.vinhsang.vivmall.domain.repository.ProductRepositoty;
import com.example.vinhsang.vivmall.presentation.view.activity.mainactivity.MainActivity;
import com.example.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment.AllFragment;
import com.example.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment.CatalogueFragment;
import com.example.vinhsang.vivmall.data.datamanager.DataInterface;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Long on 7/8/2016.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    DataInterface getDataManager();

    void inject(MainActivity mainActivity);
    void inject(AllFragment allFragment);
    void inject(CatalogueFragment catalogueFragment);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    ProductRepositoty productRepositoty();
}
