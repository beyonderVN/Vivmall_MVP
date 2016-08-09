package com.vinhsang.vivmall.presentation.internal.di;

import android.content.Context;

import com.vinhsang.vivmall.presentation.view.activity.mainactivity.MainActivity;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment.AllFragment;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment.CatalogueFragment;
import com.vinhsang.vivmall.data.datamanager.DataInterface;
import com.vinhsang.vivmall.domain.executor.PostExecutionThread;
import com.vinhsang.vivmall.domain.executor.ThreadExecutor;
import com.vinhsang.vivmall.domain.repository.ProductRepositoty;
import com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment2.CatalogueFragment2;

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
    void inject(CatalogueFragment2 catalogueFragment);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    ProductRepositoty productRepositoty();
}
