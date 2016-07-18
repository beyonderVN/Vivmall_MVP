package com.example.vinhsang.vivmall.datamanager;

import android.content.Context;
import android.util.Log;

import com.example.vinhsang.vivmall.helper.Extra;
import com.example.vinhsang.vivmall.model.ItemProduct;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Long on 7/14/2016.
 */
@Singleton
public class DataManager extends BaseDataManager {
    Context context;
    int page;
    int lastPosition;
    @Inject
    public DataManager(Context context) {
        this.context = context;
        this.page = 0;
        this.lastPosition = 1;
    }


    @Override
    public List<ItemProduct> itemProducts() {
        //return Utils.getListItemProduct(context);
        List<ItemProduct> itemProducts = new ArrayList<>();
        try {
            itemProducts = new Extra.MyTaskLoadProduct().execute(String.valueOf(lastPosition)).get();
            page ++;
            lastPosition = lastPosition +itemProducts.size();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        Log.d(this.getClass().getCanonicalName(), "itemProducts: "+itemProducts.size());
        return itemProducts;
    }

    @Override
    public ItemProduct itemProduct() {
        return null;
    }
}
