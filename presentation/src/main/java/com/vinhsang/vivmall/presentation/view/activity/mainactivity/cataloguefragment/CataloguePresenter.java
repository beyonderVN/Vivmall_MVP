package com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment;

import com.vinhsang.vivmall.domain.Catalogue;
import com.vinhsang.vivmall.domain.ItemProduct;
import com.vinhsang.vivmall.domain.interactor.DefaultSubscriber;
import com.vinhsang.vivmall.domain.interactor.UseCase;
import com.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

/**
 * Created by Long on 7/8/2016.
 */

public class CataloguePresenter extends SimpleMVPPresenter<CatalogueView, CataloguePresentationModel> {
    private static final String TAG = "CataloguePresenter";
    private final UseCase productCase;
    private final UseCase productListByCata;

    @Inject
    public CataloguePresenter(@Named("catalogueList") UseCase productCase,
                              @Named("productListByCata") UseCase productListByCata) {
        this.productCase = productCase;
        this.productListByCata = productListByCata;
    }

    @Override
    public void attachView(CatalogueView mvpView, CataloguePresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        if (getMvpView() != null) {
            getMvpView().init();
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
        productListByCata.execute(new LoadMoreItemByCata(), getPresentationModel().getTagId(), getPresentationModel().getLastItem());

    }

    public void resetRecyclerViewByNewTag(String tag) {
        getPresentationModel().reset();
        if (getMvpView() != null) {
            getMvpView().showProgress();
        }
        getPresentationModel().setCurrentTag(tag);
        productListByCata.execute(new LoadFirstItemsByCata(), getPresentationModel().getTagId(), getPresentationModel().getLastItem());
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
            getPresentationModel().setListTag(catalogues);
            getPresentationModel().setCurrentTag("Điện tử");
            if (getMvpView() != null) {
                getMvpView().showContent();
                getMvpView().loadListTag();
            }
            productListByCata.execute(new LoadFirstItemsByCata(), getPresentationModel().getTagId(), getPresentationModel().getLastItem());
        }
    }

    public class LoadFirstItemsByCata extends DefaultSubscriber<List<ItemProduct>> {
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
            getPresentationModel().loadMore(itemProducts);
            getMvpView().init();
            if (getMvpView() != null) {
                getMvpView().showContent();
            }
        }
    }

    public class LoadMoreItemByCata extends DefaultSubscriber<List<ItemProduct>> {
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

            if (getMvpView() != null) {
                getMvpView().finishLoadingMore();
            }
            getPresentationModel().loadMore(itemProducts);
            if (getMvpView() != null) {
                getMvpView().onUpdate();
            }
        }
    }
    public void resetRecyclerView(){

    }
}
