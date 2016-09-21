package com.vinhsang.vivmall.presentation.ui.activity.mainactivity.cataloguefragment;

import com.vinhsang.vivmall.domain.ItemProduct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 7/8/2016.
 */

public class CataloguePresentationModel implements Serializable {
    private List<ItemProduct> mItemProducts = new ArrayList<>();
    private String           tittle;
    private String[] listTag;
    private int loadItemNum = 6;
    private String currentTag;
    public String getCurrentTag() {
        return currentTag;
    }

    public void setCurrentTag(String currentTag) {
        this.currentTag = currentTag;
    }


    public String[] getListTag() {
        return listTag;
    }

    public void setListTag(String[] listTag) {
        this.listTag = listTag;
    }


    public int getLastItem() {
        return lastItem;
    }

    public int getLoadItemNum() {
        return loadItemNum;
    }

    public void setLoadItemNum(int loadItemNum) {
        this.loadItemNum = loadItemNum;
    }

    public void setLastItem(int lastItem) {
        this.lastItem = lastItem;
    }

    int lastItem = 0;
    public CataloguePresentationModel(String tittle) {
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

    public int getTagId(){
        int id = 1;
        for (int i = 0; i < listTag.length; i++) {
            if(listTag[i].equals(currentTag) ){
               id = id + i;
            }


        }
        return id;
    }
}
