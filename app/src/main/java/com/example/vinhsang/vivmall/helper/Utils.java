package com.example.vinhsang.vivmall.helper;

import android.content.Context;
import android.util.Log;

import com.example.vinhsang.vivmall.model.ItemProduct;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by Long on 7/7/2016.
 */

public class Utils {
    private static final String TAG = "Utils";
    public static String getListItemProductString(Context context   ){
        String json = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(context.getAssets().open("listItemproduct.json"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                json = json+mLine;
            }
        } catch (IOException e) {
            Log.e(TAG, "init: ",e );
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }
        return json;
    }
    public static ArrayList<ItemProduct> getListItemProduct(Context context ){

        String json = "";
        BufferedReader reader = null;
        ArrayList<ItemProduct> itemProductList = new ArrayList<>();
        try {
            Log.d(TAG, "getListItemProduct: "+context);
            reader = new BufferedReader(
                     new InputStreamReader(context.getAssets().open("listItemproduct.json"), "UTF-8"));

            // do reading, usually loop until end of file reading
            String mLine;
            while ((mLine = reader.readLine()) != null) {
                json = json+mLine;
            }
            if(json!=null && json.length()>0){
                JSONArray jsonArray = new JSONArray(json);
                for (int i=0; i<jsonArray.length(); i++) {
                    String strobj = jsonArray.getString(i);
                    ItemProduct item = Extra.getItemProductFromJson(strobj);
                    itemProductList.add(item);
                }
            }
        } catch (IOException e) {
            Log.e(TAG, "init: ",e );
            //log the exception
        } catch (JSONException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    //log the exception
                }
            }
        }

        return itemProductList;
    }
}
