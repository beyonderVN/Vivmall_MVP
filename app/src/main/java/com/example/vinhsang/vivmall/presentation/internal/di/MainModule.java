package com.example.vinhsang.vivmall.presentation.internal.di;

import android.content.Context;

import com.example.vinhsang.vivmall.data.cache.ProductCache;
import com.example.vinhsang.vivmall.data.cache.ProductCacheImpl;
import com.example.vinhsang.vivmall.data.executor.JobExecutor;
import com.example.vinhsang.vivmall.data.repository.ProductDataRepository;
import com.example.vinhsang.vivmall.domain.executor.PostExecutionThread;
import com.example.vinhsang.vivmall.domain.executor.ThreadExecutor;
import com.example.vinhsang.vivmall.domain.interactor.GetProductList;
import com.example.vinhsang.vivmall.domain.interactor.ProductCase;
import com.example.vinhsang.vivmall.domain.repository.ProductRepositoty;
import com.example.vinhsang.vivmall.presentation.MainApplication;
import com.example.vinhsang.vivmall.presentation.UIThread;
import com.example.vinhsang.vivmall.data.datamanager.DataInterface;
import com.example.vinhsang.vivmall.data.datamanager.DataManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Long on 7/8/2016.
 */
@Module
public class MainModule {
    private String baseUrl;
    private Context context;
    private final MainApplication application;

    public MainModule(MainApplication application) {
        this.application = application;
    }

    @Provides @Singleton Context provideApplicationContext() {
        return this.application;
    }

    @Provides @Singleton
    DataInterface getDataManager(){
        DataInterface dataManager = new DataManager(context);
        return dataManager;
    }

    @Provides @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

    @Provides @Singleton
    ProductCache provideProductCache(ProductCacheImpl productCache) {
        return productCache;
    }

    @Provides @Singleton
    ProductRepositoty provideUserRepository(ProductDataRepository productDataRepository) {
        return productDataRepository;
    }

    @Provides @Singleton
    ProductCase provideGetProductListUseCase(
            GetProductList getProductList) {
        return getProductList;
    }
}
