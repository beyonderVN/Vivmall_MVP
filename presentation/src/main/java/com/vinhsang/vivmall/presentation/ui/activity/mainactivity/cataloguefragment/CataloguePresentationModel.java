package com.vinhsang.vivmall.presentation.ui.activity.mainactivity.cataloguefragment;

import android.util.Log;

import com.vinhsang.vivmall.domain.Catalogue;
import com.vinhsang.vivmall.presentation.model.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 7/8/2016.
 */

public class CataloguePresentationModel implements Serializable {
    private static final String TAG = "CataloguePresentationMo";
    private List<BaseModel> mItemProducts = new ArrayList<BaseModel>();
    private String tittle;
    private List<Catalogue> listTag = new ArrayList<>();
    private String currentTag;
    int lastItem = 0;
    public String getCurrentTag() {
        return currentTag;
    }

    public void setCurrentTag(String currentTag) {
        this.currentTag = currentTag;
    }


    public List<Catalogue> getListTag() {
        return listTag;
    }

    public List<String> getListTagString() {
        List<String> list = new ArrayList<>();
        for (Catalogue catalogue : listTag
                ) {
            list.add(catalogue.getTitle());
        }
        return list;
    }

    public void setListTag(List<Catalogue> listTag) {
        Log.d(TAG, "setListTag: "+listTag.size());
        this.listTag = listTag;
    }


    public int getLastItem() {
        return lastItem;
    }



    public void setLastItem(int lastItem) {
        this.lastItem = lastItem;
    }



    public CataloguePresentationModel(String tittle) {
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
        return listTag.size() == 0;
    }

    public int getTagId() {
        Log.d(TAG, "currentTag: "+currentTag);
        Log.d(TAG, "currentTag: "+listTag.size());
        for (int i = 0; i < listTag.size(); i++) {
            Log.d(TAG, "listTag: " +listTag.get(i).getTitle());
            if(currentTag.equals(listTag.get(i).getTitle())){
                return listTag.get(i).getId();
            }
        }
        return 0;
    }
    public void loadMore(List<BaseModel> listItemProduct) {
        lastItem = lastItem + listItemProduct.size();
        mItemProducts.addAll(listItemProduct);
    }
    public void reset(){
        mItemProducts.clear();
        lastItem=0;
    }

    boolean noMore =false;

    public boolean isNoMore() {
        return noMore;
    }

    public void setNoMore(boolean noMore) {
        this.noMore = noMore;
    }
}
