package com.vinhsang.vivmall.presentation.ui.activity.mainactivity.cataloguefragment2;

import com.vinhsang.vivmall.presentation.model.BaseModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Long on 7/8/2016.
 */

public class CataloguePresentationModel2 implements Serializable {
    private static final String TAG = "CataloguePresentationMo";
    private List<BaseModel>  baseModels = new ArrayList<BaseModel>();
    private String tittle;
    int lastItem = 0;
    int position = 0;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getLastItem() {
        return lastItem;
    }



    public void setLastItem(int lastItem) {
        this.lastItem = lastItem;
    }



    public CataloguePresentationModel2(String tittle) {
        this.tittle = tittle;
    }

    public List<BaseModel> getmBaseModels() {
        return baseModels;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public boolean shouldFetchRepositories() {
        return baseModels.size() == 0;
    }


    public void loadMore(List<BaseModel> listItemProduct) {
        lastItem = lastItem + listItemProduct.size();
        baseModels.addAll(listItemProduct);
    }
    public void reset(){
        baseModels.clear();
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
