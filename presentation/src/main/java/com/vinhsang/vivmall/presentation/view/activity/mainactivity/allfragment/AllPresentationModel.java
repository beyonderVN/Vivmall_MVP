package com.vinhsang.vivmall.presentation.view.activity.mainactivity.allfragment;

import android.util.Log;

import com.vinhsang.vivmall.domain.ItemProduct;
import com.vinhsang.vivmall.presentation.view.activity.base.OneInterfaceForAll;

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
    int lastItem = 0;
    List<OneInterfaceForAll> listDataCallbackses;

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
        //List<ItemProductEntity> listItemProduct = new Extra.MyTaskLoadProduct().execute("", Integer.toString(lastItem)).get();
        lastItem = lastItem + listItemProduct.size();
        mItemProducts.addAll(listItemProduct);
        mItemProducts.addAll(listItemProduct);
    }
    public void clearListItemProduct() {
        mItemProducts.clear();
        lastItem = 0;
        for (OneInterfaceForAll oneInterfaceForAll : listDataCallbackses) {
            oneInterfaceForAll.update(AllPresenter.Work.RESET_LIST,null);
        }
    }

    public void registerCallback(OneInterfaceForAll callbacks){
        if(listDataCallbackses == null){
            listDataCallbackses = new ArrayList<>();
        }
        if(!listDataCallbackses.contains(callbacks)){
            listDataCallbackses.add(callbacks);
            Log.d(TAG, "registerCallback: "+ listDataCallbackses.size());
        }

    }

    public void unregisterCallback(OneInterfaceForAll callbacks){
        listDataCallbackses.remove(callbacks);
    }


}
