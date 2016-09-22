package com.vinhsang.vivmall.presentation.internal.di;

import android.content.Context;

import com.vinhsang.vivmall.data.bourbon.BourbonService;
import com.vinhsang.vivmall.data.bourbon.DataManager;
import com.vinhsang.vivmall.domain.executor.PostExecutionThread;
import com.vinhsang.vivmall.domain.executor.ThreadExecutor;
import com.vinhsang.vivmall.domain.repository.ProductRepositoty;
import com.vinhsang.vivmall.presentation.ui.activity.detailactivity.ItemDetailActivity;
import com.vinhsang.vivmall.presentation.ui.activity.main.MainActivity;
import com.vinhsang.vivmall.presentation.ui.activity.main.allfragment.AllFragment;
import com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2.AllFragment2;
import com.vinhsang.vivmall.presentation.ui.activity.main.cataloguefragment.CatalogueFragment;
import com.vinhsang.vivmall.presentation.ui.activity.main.cataloguefragment2.CatalogueFragment2;
import com.vinhsang.vivmall.presentation.ui.activity.shotdetail.ShotActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Long on 7/8/2016.
 */
@Singleton
@Component(modules = {MainModule.class})
public interface MainComponent {

    DataManager dataManager();
    BourbonService bourbonService();

    void inject(MainActivity mainActivity);
    void inject(AllFragment allFragment);
    void inject(AllFragment2 allFragment2);
    void inject(CatalogueFragment catalogueFragment);
    void inject(CatalogueFragment2 catalogueFragment);
    void inject(ItemDetailActivity itemDetailActivity);
    void inject(ShotActivity shotActivity);

    Context context();
    ThreadExecutor threadExecutor();
    PostExecutionThread postExecutionThread();
    ProductRepositoty productRepositoty();
}
