package com.example.vinhsang.vivmall.activity.mainactivity.allfragment;

import android.util.Log;

import com.example.vinhsang.vivmall.helper.Extra;
import com.example.vinhsang.vivmall.model.ItemProduct;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Created by Long on 7/8/2016.
 */

public class AllPresentationModel implements Serializable {
    private static final String TAG = "AllPresentationModel";
    private List<ItemProduct> mItemProducts = new ArrayList<ItemProduct>();
    private String tittle;
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

    public void loadMore() {
        Log.d(TAG, "loadMore: "+mItemProducts.size());
        try {
            List<ItemProduct> listItemProduct = new Extra.MyTaskLoadProduct().execute("", Integer.toString(lastItem)).get();
            lastItem = lastItem + listItemProduct.size();
            mItemProducts.addAll(listItemProduct);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

}
