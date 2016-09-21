package com.vinhsang.vivmall.presentation.internal.di;

import android.content.Context;

import com.vinhsang.vivmall.data.datamanager.DataInterface;
import com.vinhsang.vivmall.domain.executor.ThreadExecutor;
import com.vinhsang.vivmall.domain.repository.ProductRepositoty;
import com.vinhsang.vivmall.presentation.ui.activity.main.MainActivity;
import com.vinhsang.vivmall.domain.executor.PostExecutionThread;
import com.vinhsang.vivmall.presentation.ui.activity.main.allfragment.AllFragment;
import com.vinhsang.vivmall.presentation.ui.activity.main.cataloguefragment.CatalogueFragment;

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
