package com.vinhsang.vivmall.presentation.internal.di;

import android.content.Context;

import com.vinhsang.vivmall.data.cache.ProductCache;
import com.vinhsang.vivmall.data.cache.ProductCacheImpl;
import com.vinhsang.vivmall.data.executor.JobExecutor;
import com.vinhsang.vivmall.data.repository.ProductDataRepository;
import com.vinhsang.vivmall.domain.executor.PostExecutionThread;
import com.vinhsang.vivmall.domain.executor.ThreadExecutor;
import com.vinhsang.vivmall.domain.interactor.GetCatalogueList;
import com.vinhsang.vivmall.domain.interactor.GetProductListAll;
import com.vinhsang.vivmall.domain.interactor.GetProductListByCataloge;
import com.vinhsang.vivmall.domain.interactor.UseCase;
import com.vinhsang.vivmall.domain.repository.ProductRepositoty;
import com.vinhsang.vivmall.presentation.MainApplication;
import com.vinhsang.vivmall.presentation.UIThread;
import com.vinhsang.vivmall.data.datamanager.DataInterface;
import com.vinhsang.vivmall.data.datamanager.DataManager;

import javax.inject.Named;
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

    @Provides  @Named("userList")
    UseCase provideGetProductListUseCase(
            GetProductListAll getProductList) {
        return getProductList;
    }

    @Provides  @Named("catalogueList")
    UseCase provideGetCatalogueList(
            GetCatalogueList getCatalogueList) {
        return getCatalogueList;
    }
    @Provides  @Named("productListByCata")
    UseCase provideGetProductListByCataUseCase(
            GetProductListByCataloge getProductListByCataloge) {
        return getProductListByCataloge;
    }
}

