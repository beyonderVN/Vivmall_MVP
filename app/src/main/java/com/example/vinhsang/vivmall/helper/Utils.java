package com.example.vinhsang.vivmall.helper;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import com.example.vinhsang.vivmall.MainApplication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Long on 7/7/2016.
 */

public class Utils {
    private static final String TAG = "Utils";
    public static String getListItemProduct(Activity activity   ){
        String json = "";
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(activity.getAssets().open("listItemproduct.json"), "UTF-8"));

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
    public static String getListItemProduct(){
        Context context = MainApplication.mContext;
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
}
