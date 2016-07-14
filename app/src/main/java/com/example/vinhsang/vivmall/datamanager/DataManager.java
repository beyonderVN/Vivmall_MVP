package com.example.vinhsang.vivmall.datamanager;

import android.util.Log;

import com.example.vinhsang.vivmall.activity.mainactivity.allfragment.AllPresentationModel;

import java.util.ArrayList;
import java.util.List;

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
    private List<DataLoadingCallbacks> loadingCallbacks;
    public DataManager() {

        allPresentationModel = new AllPresentationModel("All");
    }

    @Override
    public boolean isDataLoading() {
        return false;
    }

    @Override
    public void registerCallback(DataLoadingCallbacks callbacks) {
        if (loadingCallbacks == null) {
            loadingCallbacks = new ArrayList<>(1);
        }
        loadingCallbacks.add(callbacks);
    }

    @Override
    public void unregisterCallback(DataLoadingCallbacks callbacks) {
        if (loadingCallbacks != null && loadingCallbacks.contains(callbacks)) {
            loadingCallbacks.remove(callbacks);
        }
    }

    @Override
    public AllPresentationModel getAllPresentationMOdel() {
        count++;
        Log.d(TAG, "DataManager: "+count);
        return allPresentationModel;
    }
}
