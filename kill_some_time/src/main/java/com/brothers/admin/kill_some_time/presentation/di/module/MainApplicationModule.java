package com.brothers.admin.kill_some_time.presentation.di.module;

import android.content.Context;

import com.brothers.admin.kill_some_time.data.executor.JobExecutor;
import com.brothers.admin.kill_some_time.data.repository.DataRepository;
import com.brothers.admin.kill_some_time.domain.executor.PostExecutionThread;
import com.brothers.admin.kill_some_time.domain.executor.ThreadExecutor;
import com.brothers.admin.kill_some_time.domain.interactor.GetList;
import com.brothers.admin.kill_some_time.domain.interactor.UseCase;
import com.brothers.admin.kill_some_time.domain.repository.Repositoty;
import com.brothers.admin.kill_some_time.presentation.MainApplication;
import com.brothers.admin.kill_some_time.presentation.UIThread;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Long on 7/21/2016.
 */
@Module
public class MainApplicationModule {
    private final MainApplication application;

    public MainApplicationModule(MainApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }
//
//    @Provides @Singleton
//    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
//        return jobExecutor;
//    }
//
//    @Provides @Singleton
//    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
//        return uiThread;
//    }
//
//    @Provides @Singleton
//    ProductCache provideProductCache(ProductCacheImpl productCache) {
//        return productCache;
//    }

    @Provides @Singleton
    Repositoty provideRepositoty(DataRepository dataRepository) {
        return dataRepository;
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
    UseCase provideGetItemListUseCase(
            GetList getProductList) {
        return getProductList;
    }
}
