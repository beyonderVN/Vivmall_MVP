package com.vinhsang.vivmall.presentation.ui.activity.mainactivity.cataloguefragment2;

import android.util.Log;

import com.vinhsang.vivmall.domain.Catalogue;
import com.vinhsang.vivmall.domain.ItemProduct;
import com.vinhsang.vivmall.domain.interactor.DefaultSubscriber;
import com.vinhsang.vivmall.domain.interactor.UseCase;
import com.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;
import com.vinhsang.vivmall.presentation.mapper.ItemProductModelDataMapper;
import com.vinhsang.vivmall.presentation.ui.activity.mainactivity.cataloguefragment2.adapter.SectionDataModel;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Long on 7/8/2016.
 */

public class CataloguePresenter2 extends SimpleMVPPresenter<CatalogueView2, CataloguePresentationModel2> {
    private static final String TAG = "CataloguePresenter2";
    private final UseCase productCase;
    private final UseCase productListByCata;
    private final ItemProductModelDataMapper itemProductModelDataMapper;
    @Inject
    public CataloguePresenter2(@Named("catalogueList") UseCase productCase,
                               @Named("productListByCata") UseCase productListByCata,
                               ItemProductModelDataMapper itemProductModelDataMapper) {
        this.productCase = productCase;
        this.productListByCata = productListByCata;
        this.itemProductModelDataMapper = itemProductModelDataMapper;
    }

    @Override
    public void attachView(CatalogueView2 mvpView, CataloguePresentationModel2 presentationModel) {
        super.attachView(mvpView, presentationModel);
        if (getMvpView() != null) {
            getMvpView().loadListTag();
        }
        if (presentationModel.shouldFetchRepositories()) {
            init();
        }
    }

    public void init() {
        if (getMvpView() != null) {
            getMvpView().showProgress();
        }
        productCase.execute(new LoadCatalogueList());
    }

    public void loadMore() {
        if (getMvpView() != null) {
            getMvpView().startLoadingMore();
        }
        //productListByCata.execute(new LoadMoreItemByCata(), getPresentationModel().getTagId(), getPresentationModel().getLastItem());

    }



    public class LoadCatalogueList extends DefaultSubscriber<List<Catalogue>> {

        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<Catalogue> catalogues) {
            super.onNext(catalogues);
            for (int i = 0; i < catalogues.size(); i++) {
                getPresentationModel().getmBaseModels().add(new SectionDataModel(catalogues.get(i)));
            }
            for (int i = 0; i < catalogues.size(); i++) {
                productListByCata.execute(new LoadItemsByCata(), catalogues.get(i).getId(), 0);

            }

        }
    }

    public class LoadItemsByCata extends DefaultSubscriber<List<ItemProduct>> {
        @Override
        public void onCompleted() {
            super.onCompleted();
        }

        @Override
        public void onError(Throwable e) {
            super.onError(e);
        }

        @Override
        public void onNext(List<ItemProduct> itemProducts) {
            super.onNext(itemProducts);
            Log.d(TAG, "onNext: "+itemProducts.size());
            ((SectionDataModel) getPresentationModel().getmBaseModels().get(getPresentationModel().getPosition())).setAllItemsInSection(itemProductModelDataMapper.transform(itemProducts));
            getPresentationModel().setPosition(getPresentationModel().getPosition()+1);
            if (getMvpView() != null) {
                getMvpView().onUpdate();
                if(getPresentationModel().getPosition()==(getPresentationModel().getmBaseModels().size()-1)){
                    getMvpView().showContent();
                }

            }
        }
    }



}
