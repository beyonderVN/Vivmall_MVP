package com.vinhsang.vivmall.presentation.view.activity.mainactivity.cataloguefragment;

import android.util.Log;

import com.vinhsang.vivmall.presentation.coremvp.SimpleMVPPresenter;
import com.vinhsang.vivmall.presentation.helper.Extra;
import com.vinhsang.vivmall.domain.ItemProduct;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by Long on 7/8/2016.
 */

public class CataloguePresenter extends SimpleMVPPresenter<CatalogueView, CataloguePresentationModel> {
    private static final String TAG = "CataloguePresenter";
    @Inject
    public CataloguePresenter() {
    }

    @Override
    public void attachView(CatalogueView mvpView, CataloguePresentationModel presentationModel) {
        super.attachView(mvpView, presentationModel);
        if(presentationModel.shouldFetchRepositories()){
            //init();
        }
    }

    public void init() {

        if (getMvpView() != null) {
            getMvpView().showProgress();
        }
        List<ItemProduct> listItemProduct = new ArrayList<>();
        String[] tags = new String[]{"Điện tử", "Thiết bị thông minh", "Phụ kiện điện tử","Headphone","Kính mát","Đèn Led"};
        getPresentationModel().setListTag(tags);
        try{

            int last = getPresentationModel().getLastItem();
            getPresentationModel().setCurrentTag("Điện tử");
            listItemProduct = new Extra.MyTaskLoadProductbyCt().execute("0" + getPresentationModel().getTagId(), Integer.toString(last)).get();
            Log.d(TAG, "init: "+listItemProduct.size()+getPresentationModel().getCurrentTag() );
            getPresentationModel().setLastItem(last+getPresentationModel().getLoadItemNum());
            showContent(listItemProduct);
        }catch (Exception e){
            e.getStackTrace();
            onFetchError();
        }

    }

    private void showContent(List<ItemProduct> listItemProduct) {
        getPresentationModel().getmItemProducts().addAll(listItemProduct);
        if (getMvpView() != null) {
            getMvpView().showContent();
        }
    }

    private void onFetchError() {
        if (getMvpView() != null) {
            getMvpView().onFetchError();
        }
    }
    public void loadMore() {
        if (getMvpView() != null) {
            getMvpView().showLoadingMore();
        }
        List<ItemProduct> listItemProduct = new ArrayList<>();
        try{
            int last = getPresentationModel().getLastItem();
            listItemProduct = new Extra.MyTaskLoadProductbyCt().execute("0" + Integer.toString(1), Integer.toString(last)).get();
            getPresentationModel().getmItemProducts().addAll(listItemProduct);
            getPresentationModel().setLastItem(last+getPresentationModel().getLoadItemNum());
            if (getMvpView() != null) {
                getMvpView().onLoadMore();
            }
        }catch (Exception e){
            e.getStackTrace();
            onFetchError();
        }

    }
    public void resetRecyclerViewByNewTag(String tag)  {
        List<ItemProduct> listItemProduct = new ArrayList<>();
        getPresentationModel().getmItemProducts().clear();
        try{
            int last = 0;
            getPresentationModel().setLastItem(last);
            getPresentationModel().setCurrentTag(tag);
            listItemProduct = new Extra.MyTaskLoadProductbyCt().execute("0" + Integer.toString(getPresentationModel().getTagId()), Integer.toString(last)).get();
            getPresentationModel().getmItemProducts().addAll(listItemProduct);
            getPresentationModel().setLastItem(last+getPresentationModel().getLoadItemNum());

            if (getMvpView() != null) {
                getMvpView().onLoadMore();
            }
        }catch (Exception e){
            e.getStackTrace();
            onFetchError();
        }
    }
}
