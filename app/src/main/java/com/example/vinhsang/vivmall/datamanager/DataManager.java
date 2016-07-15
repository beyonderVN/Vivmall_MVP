package com.example.vinhsang.vivmall.datamanager;

import com.example.vinhsang.vivmall.activity.mainactivity.allfragment.AllPresentationModel;

/**
 * Created by Long on 7/14/2016.
 */

public class DataManager extends BaseDataManager {
    private static final String TAG = "DataManager";
    private static DataManager dataManager = new DataManager();
    public static DataManager getInstance() {
        return dataManager;
    }
    static int count = 0;
    static AllPresentationModel allPresentationModel;
    public DataManager() {

        allPresentationModel = new AllPresentationModel("All");
    }

    public AllPresentationModel getAllPresentationMOdel() {
//        count++;
//        Log.d(TAG, "DataManager: "+count);
        return allPresentationModel;
    }
}
