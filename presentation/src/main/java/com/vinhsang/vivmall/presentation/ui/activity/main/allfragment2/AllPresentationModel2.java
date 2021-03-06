package com.vinhsang.vivmall.presentation.ui.activity.main.allfragment2;

import android.util.Log;

import com.vinhsang.vivmall.presentation.model.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Long on 7/8/2016.
 */

public class AllPresentationModel2 implements Serializable {
    private static final String TAG = "AllPresentationModel2";
    private List<BaseModel> mItemProducts = new ArrayList<BaseModel>();
    private String tittle;

    public int getLastItem() {
        return lastItem;
    }

    public void setLastItem(int lastItem) {
        this.lastItem = lastItem;
    }

    int lastItem = 0;


    public AllPresentationModel2(String tittle) {
        this.tittle = tittle;
    }

    public List<BaseModel> getmItemProducts() {
        return mItemProducts;
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


    public void loadMore(List<BaseModel> listItemProduct) {

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
