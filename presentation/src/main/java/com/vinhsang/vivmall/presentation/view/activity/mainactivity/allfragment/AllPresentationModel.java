package com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment;

import android.util.Log;

import com.vinhsang.vivmall.domain.ItemProduct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Long on 7/8/2016.
 */

public class AllPresentationModel implements Serializable {
    private static final String TAG = "AllPresentationModel";
    private List<ItemProduct> mItemProducts = new ArrayList<ItemProduct>();
    private String tittle;

    public int getLastItem() {
        return lastItem;
    }

    public void setLastItem(int lastItem) {
        this.lastItem = lastItem;
    }

    int lastItem = 0;


    public AllPresentationModel(String tittle) {
        this.tittle = tittle;
    }

    public List<ItemProduct> getmItemProducts() {
        return mItemProducts;
    }

    public void setmItemProducts(List<ItemProduct> mItemProducts) {
        this.mItemProducts = mItemProducts;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public boolean shouldFetchRepositories() {
        return mItemProducts.size() == 0;
    }


    public void loadMore(List<ItemProduct> listItemProduct) {

        lastItem = lastItem + listItemProduct.size();
        mItemProducts.addAll(listItemProduct);
        Log.d(TAG, "mItemProducts.size(): "+mItemProducts.size());
    }


    public void reset(){
        mItemProducts.clear();
        lastItem = 0;
        noMore =false;
    }

    boolean noMore =false;

    public boolean isNoMore() {
        return noMore;
    }

    public void setNoMore(boolean noMore) {
        this.noMore = noMore;
    }
}
